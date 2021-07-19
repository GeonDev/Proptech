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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    void test(){
        List<User> list = userRepository.findAll();

        list.forEach(o->{
            LoginHistory history = loginHistoryRepository.findTopByUserOrderByIdDesc(o);
            if(history!=null ){
                System.out.println(history.getLoginDate() );
            } });
    }



    @Transactional
    void joinQueryTest(){
        List<User> user = userRepository.findAllByPartnerInfo();

        user.forEach( o->{
            System.out.println(o.getName());
            System.out.println(o.getCompany().getCeoName());
        });

        for( Account a : user.get(0).getAccountList()   ){
            System.out.println(a.getBankName());
        }
        List<Account> accountList = accountRepository.findAll();

        accountList.forEach(o->{
            System.out.println(o.getBankName());
            System.out.println(o.getAccount());
        });
    }



}