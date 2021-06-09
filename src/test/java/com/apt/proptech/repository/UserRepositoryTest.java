package com.apt.proptech.repository;

import com.apt.proptech.domain.Account;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    void crud() {


        List<User> list = userRepository.findAllByUserRoles("USER");

        list.forEach(o ->{
            System.out.println(o.getId() );
            System.out.println(o.getName() );
            System.out.println(o.getUserRole() );
            System.out.println(o.getRegDate() );
        } );

        Pageable page = PageRequest.of(0,10);
        List<User> pageUser = userRepository.findAllByUserState(UserState.AUTH, page);

        pageUser.forEach(o ->{
            System.out.println(o.getId() );
            System.out.println(o.getName() );
            System.out.println(o.getUserState() );
            System.out.println(o.getRegDate() );
        } );
    }

    @Test
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