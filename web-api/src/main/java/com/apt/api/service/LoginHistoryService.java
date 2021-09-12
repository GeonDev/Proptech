package com.apt.api.service;


import com.apt.api.domain.LoginHistory;
import com.apt.api.domain.User;
import com.apt.api.domain.dto.LoginHistoryDto;
import com.apt.api.domain.enums.IpChecked;
import com.apt.api.repository.LoginHistoryRepository;
import com.apt.api.repository.UserRepository;
import com.apt.api.repository.support.LoginHistoryRepositorySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class LoginHistoryService {

    static final Logger LOGGER = LoggerFactory.getLogger(LoginHistoryService.class);

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Autowired
    private LoginHistoryRepositorySupport loginHistoryRepositorySupport;

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
    public List<LoginHistoryDto> getLoginHistoryExceptIpChecked(User user ,IpChecked ipChecked, int count){
        List<LoginHistoryDto> list = new ArrayList<>();

        //List<LoginHistory> temp = loginHistoryRepository.findByUserAndExceptIpChecked(ipChecked, user);

        List<LoginHistory> temp = loginHistoryRepositorySupport.findLoginHistoryLimitAndOrder(user, ipChecked, "except", count, "desc");

        if(temp !=null && !temp.isEmpty() ){
            for( LoginHistory history : temp ){
                list.add( new LoginHistoryDto(history));
            }
        }

        return  list;
    }


    //특정 IP 정보를 위한 메소드
    public List<LoginHistoryDto> getLoginHistoryIpChecked(User user ,IpChecked ipChecked, int count){
        List<LoginHistoryDto> list = new ArrayList<>();

        //List<LoginHistory> temp = loginHistoryRepository.findByUserAndIpChecked(ipChecked, user);

        List<LoginHistory> temp = loginHistoryRepositorySupport.findLoginHistoryLimitAndOrder(user, ipChecked, "only", count, "desc" );

        if(temp !=null && !temp.isEmpty() ){
            for( LoginHistory history : temp ){
                list.add( new LoginHistoryDto(history));
            }
        }

        return  list;
    }

    public void deleteLoginHistory(Long id ){
        LoginHistory temp = loginHistoryRepository.findById(id).orElse(null);

        if(temp !=null ){
            loginHistoryRepository.delete(temp);
            LOGGER.info("[INFO] DELETE LOGIN HISTORY ");
        }

    }



}
