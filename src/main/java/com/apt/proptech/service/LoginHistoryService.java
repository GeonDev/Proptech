package com.apt.proptech.service;

import com.apt.proptech.repository.LoginHistoryRepository;
import com.apt.proptech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


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


}
