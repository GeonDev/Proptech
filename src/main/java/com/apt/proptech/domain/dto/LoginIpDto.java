package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.LoginIp;
import lombok.Getter;

@Getter
public class LoginIpDto {

    private String id;

    private String ip;

    private String isActive;

    public LoginIpDto(LoginIp ip){

        this.id = String.valueOf(ip.getId());
        this.ip = ip.getIp();

        if(ip.getIsActive()){
            this.isActive = "ACTIVE";
        }else{
            this.isActive = "UN_ACTIVE";
        }
    }

}
