package com.apt.proptech.repository;

import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.AssociateUser;
import com.apt.proptech.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AssociateRepositoryTest {

    @Autowired
    private  AssociateRepository associateRepository;

    @Test
    @Transactional
    void crud(){
        Associate associate = associateRepository.findById(1l).orElse(null);

        List<AssociateUser> users = associate.getUserInfoList();

        for(AssociateUser temp : users ){
            System.out.println(temp.getUserInfo().getName());
        }


    }
}