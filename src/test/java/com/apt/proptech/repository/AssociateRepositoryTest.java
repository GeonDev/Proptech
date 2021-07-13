package com.apt.proptech.repository;

import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.ClaimProp;
import com.apt.proptech.domain.SaleProp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;


@SpringBootTest
class AssociateRepositoryTest {

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private SalePropRepository salePropRepository;

    @Autowired
    private ClaimRepository claimRepository;


    @Test
    @Transactional
    void paymentTest() {

        List<SaleProp> list1 = salePropRepository.findAll();
        List<ClaimProp> list2 = claimRepository.findAll();

        list1.forEach(o->{
            System.out.println(o.getId() + " "+ o.getSaleRound() +" " +o.getAddressDetail());

            o.getClaimPropList().forEach( v->{  System.out.println(v.getPayment() +" "+v.getDescription() );  });

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