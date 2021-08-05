package com.apt.proptech.repository;

import com.apt.proptech.domain.Account;
import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import com.apt.proptech.repository.support.UserRepositorySupport;
import com.apt.proptech.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongToIntFunction;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Autowired
    private UserRepositorySupport userRepositorySupport;



    @Transactional
    void crud() {

        Pageable page = PageRequest.of(0,10);
        Page<User> list = userRepository.findAllByUserRoles("ROLE_USER",page);

        list.forEach(o ->{
            System.out.println(o.getId() );
            System.out.println(o.getName() );
            System.out.println(o.getUserRole() );
            System.out.println(o.getRegDate() );
        } );

        Page<User> pageUser = userRepository.findAllByUserState("AUTH", page);

        pageUser.forEach(o ->{
            System.out.println(o.getId() );
            System.out.println(o.getName() );
            System.out.println(o.getUserState() );
            System.out.println(o.getRegDate() );
        } );
    }


    @Test
    @Transactional
    void UserDetailTest(){

        User user = userRepository.findByUsername("Admin");

        System.out.println("---------------------------------------------");

        user.getLoginHistoryList().stream().forEach( loginHistory -> {
            System.out.println(loginHistory.getId() +" " + loginHistory.getLoginDate() );
        });

        System.out.println("---------------------------------------------");

    }





}