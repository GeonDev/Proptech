package com.apt.proptech.service;

import com.apt.proptech.domain.Alert;
import com.apt.proptech.domain.User;
import com.apt.proptech.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertService {

     @Autowired
     private AlertRepository alertRepository;

    public List<Alert> getAlertListNotRead(User receiveUser ){
        return alertRepository.findTop10ByReceiveUserAndReadDateIsNull(receiveUser);
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
