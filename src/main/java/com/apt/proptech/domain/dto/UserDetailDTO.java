package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.*;
import com.apt.proptech.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDto {

    //로그인시 사용하는 유저 ID
    private String userName;
    private String name;
    private String email;
    private String profileImg;
    private String phoneNumber;
    private String provider;
    private String role;
    private String state;
    private String registerDate;

    private String ceoName;
    private String bizNum;

    private String retiredDate;

    private List<LoginHistory> loginHistoryList = new ArrayList<>();

    private List<LoginIp> loginIpList = new ArrayList<>();

    private List<Account> accountList = new ArrayList<>();

    public  UserDetailDto(User user ){
        this.userName =  user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImg = user.getProfileImg();

        this.phoneNumber = user.getPhoneNumber();

        this.provider = user.getProvider();

        this.role = user.getUserRole().getTitle();

        this.state = user.getUserState().getTitle();

        this.registerDate = CommonUtil.toDateStr(user.getRegDate());

        this.retiredDate = CommonUtil.toDateStr(user.getRetiredDate());


        if(user.getCompany() != null){
            this.ceoName = user.getCompany().getCeoName();
            this.bizNum = user.getCompany().getBizRegNum();
        }else{
            this.ceoName = "";
            this.bizNum = "";
        }

        if(user.getLoginHistoryList() != null ){
            

            this.loginHistoryList =  user.getLoginHistoryList();
        }

        if(user.getLoginIpList() != null ){
            this.loginIpList =  user.getLoginIpList();
        }

        if(user.getAccountList() != null ){
            this.accountList =  user.getAccountList();
        }


    }

}
