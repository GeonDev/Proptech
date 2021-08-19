package com.apt.proptech.service;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.dto.LoginHistoryDto;
import com.apt.proptech.domain.enums.IpChecked;
import com.apt.proptech.repository.LoginHistoryRepository;
import com.apt.proptech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class LoginHistoryService {

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    public Integer DeleteLoginHistoryBeforeMonths(int month ){

        LocalDateTime date = LocalDateTime.now().minusMonths(month );

        return loginHistoryRepository.deleteAllUserLoginHistory(date);
    }

    public List<LoginHistoryDto> getLoginHistoryInfo(User user ){
        List<LoginHistoryDto> list = new ArrayList<>();

        List<LoginHistory> temp = loginHistoryRepository.findByUserOrderByIdDesc(user);

        if(temp !=null && !temp.isEmpty() ){
            for( LoginHistory history : temp ){
                list.add( new LoginHistoryDto(history));
            }
        }

        return  list;
    }

    //특정 IP 상태를 제외한 IP 정보를 위한 메소드
    public List<LoginHistoryDto> getLoginHistoryExceptIpChecked(User user ,IpChecked ipChecked){
        List<LoginHistoryDto> list = new ArrayList<>();

        List<LoginHistory> temp = loginHistoryRepository.findByUserAndExceptIpChecked(ipChecked, user);

        if(temp !=null && !temp.isEmpty() ){
            for( LoginHistory history : temp ){
                list.add( new LoginHistoryDto(history));
            }
        }

        return  list;
    }


    //특정 IP 정보를 위한 메소드
    public List<LoginHistoryDto> getLoginHistoryIpChecked(User user ,IpChecked ipChecked){
        List<LoginHistoryDto> list = new ArrayList<>();

        List<LoginHistory> temp = loginHistoryRepository.findByUserAndIpChecked(ipChecked, user);

        if(temp !=null && !temp.isEmpty() ){
            for( LoginHistory history : temp ){
                list.add( new LoginHistoryDto(history));
            }
        }

        return  list;
    }



}
