package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.Company;
import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import com.apt.proptech.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private String provider;
    private String role;
    private String state;
    private String registerDate;

    private String ceoName;
    private String bizNum;


    private String retiredDate;
    private String modiPasswordDate;

    private String lastLoginDate;

    // 머스테치를 사용하면 필드값이 null이 들어올수 없다.
    // DTO를 이용하여 도메인의 일부 데이터만 전송하게 한다.
    public UserDto(User user, LoginHistory history){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = CommonUtil.null2str(user.getEmail());
        this.phoneNumber = CommonUtil.null2str(user.getPhoneNumber());
        this.provider = CommonUtil.null2str(user.getProvider());
        this.role = user.getUserRole().name();
        this.state = user.getUserState().name();
        this.registerDate = CommonUtil.toDateStr(user.getRegDate());
        this.retiredDate = CommonUtil.toDateStr(user.getRetiredDate());
        this.modiPasswordDate = CommonUtil.toDateStr(user.getModiPasswordDate());

        Company company = user.getCompany();

        if(company !=null ){
            this.ceoName = user.getCompany().getCeoName();
            this.bizNum = user.getCompany().getBizRegNum();
        }else{
            this.ceoName = "";
            this.bizNum = "";
        }

        if(history !=null){
            this.lastLoginDate = CommonUtil.toDateStr(history.getLoginDate());
        }else{
            this.lastLoginDate = "";
        }


    }


}
