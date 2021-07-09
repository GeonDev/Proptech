package com.apt.proptech.service;

import com.apt.proptech.domain.Alert;
import com.apt.proptech.domain.User;
import com.apt.proptech.repository.AlertRepository;
import com.apt.proptech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    @Autowired
    private UserRepository userRepository;

     @Autowired
     private AlertRepository alertRepository;

    public List<Alert> getAlertListNotRead(String userName ){

        User receiveUser = userRepository.findByUsername( userName);

        if(receiveUser != null ){
            return alertRepository.findTop10ByReceiveUserAndReadDateIsNull(receiveUser);
        }else{
            return  null;
        }
    }

    @Transactional
    public Alert sandAlert(Alert alert ){
        return alertRepository.save( alert);
    }

    public Alert readAlert(Alert alert ){
        alertRepository.findById(alert.getId() );

        Alert temp = alertRepository.findById(alert.getId()).orElse(null);

        if(temp !=null ){
            temp.setReadDate(LocalDateTime.now());
            return alertRepository.save(temp);
        }
        return  null;
    }



}
