package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.ClaimProp;
import com.apt.proptech.core.domain.SaleProp;
import com.apt.proptech.core.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;


@SpringBootTest
class AssociateRepositoryTest {

    @Autowired
    private AssociateService associateService;


    @Autowired
    private SalePropRepository salePropRepository;

    @Autowired
    private ClaimRepository claimRepository;



    @Transactional
    void paymentTest() {

        List<SaleProp> list1 = salePropRepository.findAll();
        List<ClaimProp> list2 = claimRepository.findAll();

        list1.forEach(o->{
            System.out.println(o.getId() + " "+ o.getSaleRound() +" " +o.getAddressDetail());

            o.getClaimPropList().forEach( v->{  System.out.println(v.getPayment() +" "+v.getDescription() );  });

        } );
    }


}