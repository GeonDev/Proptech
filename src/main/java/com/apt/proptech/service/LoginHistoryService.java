package com.apt.proptech.service;

import com.apt.proptech.repository.LoginHistoryRepository;
import com.apt.proptech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginHistoryService {

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    public Integer DeleteLoginHistoryBeforeMonths(int month ){

        LocalDateTime date = LocalDateTime.now().minusMonths(month );
        
        //현재는 별다른 조건 없이 전체 리스트를 받아오는 형태 -> 추후 개선
        //List<String> userIds = userRepository.findAllByUserId();

        //loginHistoryRepository.deleteAllUserLoginHistory(userIds, date);
        return loginHistoryRepository.deleteAllUserLoginHistory(date);
    }


}
