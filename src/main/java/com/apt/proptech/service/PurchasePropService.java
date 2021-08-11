package com.apt.proptech.service;

import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.PurchaseProp;
import com.apt.proptech.domain.dto.PurchaseDto;
import com.apt.proptech.domain.enums.PropType;
import com.apt.proptech.repository.PurchasePropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchasePropService {

    @Autowired
    private PurchasePropRepository purchasePropRepository;

    public List<PurchaseDto> getBuyedPurchasList(Associate associate){
        List<PurchaseDto> temp = new ArrayList<>();

        List<PurchaseProp> propList = purchasePropRepository.findByAssociateAndPropType(associate, PropType.PURCHASED);

        propList.stream().forEach(purchaseProp -> {
            temp.add(new PurchaseDto(purchaseProp) );
        } );

        return temp;
    }
}
