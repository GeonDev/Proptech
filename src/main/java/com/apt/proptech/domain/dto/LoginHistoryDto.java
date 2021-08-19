package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.enums.IpChecked;
import com.apt.proptech.util.CommonUtil;
import lombok.Getter;


@Getter
public class LoginHistoryDto {
    private Long id;

    private String loginDate;

    private String ip;

    private String state;

    //로그인 성공 여부
    private String isLogin;

    public  LoginHistoryDto(LoginHistory history){

        this.id = history.getId();
        this.ip = history.getIp();

        this.state = history.getIpChecked().name();

        this.loginDate = CommonUtil.toDateStr(history.getLoginDate());

        if(history.getIsLogin()){
            this.isLogin = "SUCCESS";
        }else{
            this.isLogin = "FAIL";
        }
    }
}
