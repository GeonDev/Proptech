package com.apt.proptech.repository;

import com.apt.proptech.domain.Account;
import com.apt.proptech.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    void crud() {
        User user = userRepository.findById(1l).orElse(null);

        List<Account> accountList = user.getAccountList();

        for(Account account : accountList){
            System.out.println(account);
        }


    }

}