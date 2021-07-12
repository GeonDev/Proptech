package com.apt.proptech.repository;

import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;


@SpringBootTest
class AssociateRepositoryTest {

    @Autowired
    private AssociateRepository associateRepository;


    @Test
    @Transactional
    void AddressTest() {

        Pageable page = PageRequest.of(0,10);
        Page<Associate> list = associateRepository.findByAssociateAddress("서울", page);

        list.forEach(o ->{
            System.out.println(o.getId() );
            System.out.println(o.getName() );
            System.out.println(o.getCity());
            System.out.println(o.getState());
            System.out.println(o.getAddress());

            o.getSalePropList().forEach(v->{
                v.getClaimList().forEach(t->{
                    System.out.println( t.getId());
                } );
            } );

        } );
    }

    @Test
    @Transactional
    void NameTest() {

        Pageable page = PageRequest.of(0,10);
        Page<Associate> list = associateRepository.findByNameContaining("조합", page);

        list.forEach(o ->{
            System.out.println(o.getId() );
            System.out.println(o.getName() );
            System.out.println(o.getCity());
            System.out.println(o.getState());
            System.out.println(o.getAddress());
        } );
    }


}