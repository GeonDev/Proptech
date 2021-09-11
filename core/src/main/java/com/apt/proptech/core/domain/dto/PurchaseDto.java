package com.apt.proptech.core.domain.dto;

import com.apt.proptech.core.domain.PropPrice;
import com.apt.proptech.core.domain.PurchaseProp;
import com.apt.proptech.core.util.CommonUtil;
import lombok.Getter;

import java.util.Comparator;

@Getter
public class PurchaseDto {

    private String address;
    private String zipCode;
    private String price;
    private String buyDate;

    public  PurchaseDto(PurchaseProp prop ){

        this.address = CommonUtil.null2str(prop.getCity()) + " "
                + CommonUtil.null2str(prop.getState()) +" "
                + CommonUtil.null2str(prop.getAddress()) +" "
                + CommonUtil.null2str(prop.getAddressDetail());

        this.zipCode = CommonUtil.null2str(prop.getZipCode());

        this.buyDate = CommonUtil.toDateStr(prop.getPurchaseDate());

        //가장 나중에 등록한 가격으로 거래 했을 것이라고 가정
        PropPrice temp =  prop.getPriceList().stream().max( Comparator.comparingLong(PropPrice::getId)).orElse(null);

        if(temp!= null){
            this.price = String.valueOf(temp.getPrice());
        }else{
            this.price = "";
        }
    }


}
