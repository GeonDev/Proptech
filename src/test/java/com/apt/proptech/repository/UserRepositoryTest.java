package com.apt.proptech.repository;

import com.apt.proptech.domain.Account;
import com.apt.proptech.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


        User user = User.builder()
                .name("MIN")
                .email("min@gmail.com")
                .phoneNumber("010-2222-3333")
                .password("1234")
                .build();

        userRepository.save(user);

        List<User> list = userRepository.findAll();

        list.forEach(o ->{
            System.out.println(o.getId() );
            System.out.println(o.getName() );
            System.out.println(o.getUserRole() );
            System.out.println(o.getRegDate() );
        } );


    }

}