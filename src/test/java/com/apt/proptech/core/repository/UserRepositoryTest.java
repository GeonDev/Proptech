package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.LoginHistory;
import com.apt.proptech.core.domain.User;
import com.apt.proptech.core.repository.support.UserRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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



    void UserDetailTest(){

        User user = userRepository.findByUsername("Admin");

        System.out.println("---------------------------------------------");

        user.getLoginHistoryList().stream().forEach( loginHistory -> {
            System.out.println(loginHistory.getId() +" " + loginHistory.getLoginDate() );
        });

        System.out.println("---------------------------------------------");

        List<LoginHistory> list =  loginHistoryRepository.findByUserOrderByIdDesc(user);
        list.stream().forEach(
                loginHistory -> {
                    System.out.println("repo -> "+ loginHistory.getId() +" " + loginHistory.getLoginDate());
                }
        );


    }
}



