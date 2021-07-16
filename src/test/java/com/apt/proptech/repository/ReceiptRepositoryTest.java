package com.apt.proptech.repository;

import com.apt.proptech.domain.Account;
import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import com.apt.proptech.repository.support.UserRepositorySupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class ReceiptRepositoryTest {

    @Autowired
    private ReceiptRepository receiptRepository;


    @Test
    @Transactional
    void test(){
        Long pay = receiptRepository.getTotalReceiptPaid();

        System.out.println(pay );


    }




}