package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.*;
import com.apt.proptech.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    public  UserDetailDto(User user ){
        this.userName =  user.getUsername();
        this.name = user.getName();
        this.email = CommonUtil.null2str(user.getEmail());
        this.profileImg = user.getProfileImg();

        this.phoneNumber =  CommonUtil.null2str(user.getPhoneNumber());

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
    }

}
