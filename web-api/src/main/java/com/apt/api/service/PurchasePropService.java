package com.apt.api.service;

import com.apt.api.domain.Associate;
import com.apt.api.domain.PurchaseProp;
import com.apt.api.repository.PurchasePropRepository;
import com.apt.api.domain.dto.PurchaseDto;
import com.apt.api.domain.enums.PropType;
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

        for (PurchaseProp purchaseProp : propList) {
            temp.add(new PurchaseDto(purchaseProp));
        }

        return temp;
    }
}
