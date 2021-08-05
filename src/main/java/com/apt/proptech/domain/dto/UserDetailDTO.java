package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.*;
import com.apt.proptech.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
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

        if(user.getLoginHistoryList() != null && !user.getLoginHistoryList().isEmpty()){
            List<LoginHistory> temp = user.getLoginHistoryList();

            temp.sort((o1, o2) -> {return (int)(o2.getId() - o1.getId()); } );

            if(temp.size() < 5){
                for(LoginHistory history : temp  ){
                    this.loginHistoryList.add(history);
                }
            }else{
                for(int i=0; i< 5; i++){
                    this.loginHistoryList.add(temp.get(i));
                }
            }
        }

        if(user.getLoginIpList() != null && !user.getLoginIpList().isEmpty()){
            List<LoginIp> temp = user.getLoginIpList();

            temp.sort((o1, o2) -> {return (int)(o2.getId() - o1.getId()); } );

            if(temp.size() < 5){
                for(LoginIp ip : temp  ){
                    this.loginIpList.add(ip);
                }
            }else{
                for(int i=0; i< 5; i++){
                    this.loginIpList.add(temp.get(i));
                }
            }

        }

        if(user.getAccountList() != null ){
            this.accountList =  user.getAccountList();
        }


    }

}
