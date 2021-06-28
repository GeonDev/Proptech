package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.Associate;
import com.apt.proptech.util.CommonUtil;

public class AssociateDto {

    private String name;
    private String round;
    private String expectDate;
    private String realDate;
    private String feeRatio;
    private String city;
    private String state;
    private String address;
    private String registerDate;
    private String modifiedDate;

    public  AssociateDto(Associate associate){
        this.name = associate.getName();
        this.round = associate.getAssociateRound().name();
        this.expectDate = CommonUtil.toDateStr(associate.getEndExpectDate());
        this.realDate = CommonUtil.toDateStr(associate.getEndRealDate());
        this.feeRatio = CommonUtil.null2str(associate.getOperateFeeRatio());
        this.city = CommonUtil.null2str(associate.getCity());
        this.state = CommonUtil.null2str(associate.getState());
        this.address = CommonUtil.null2str(associate.getState());
        this.registerDate = CommonUtil.toDateStr(associate.getRegDate());
        this.modifiedDate = CommonUtil.toDateStr(associate.getModiDate());

    }



}
