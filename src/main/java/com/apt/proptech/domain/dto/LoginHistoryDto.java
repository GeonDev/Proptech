package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.util.CommonUtil;
import lombok.Getter;


@Getter
public class LoginHistoryDto {
    private String id;

    private String loginDate;

    //로그인 성공 여부
    private String isLogin;

    public  LoginHistoryDto(LoginHistory history){

        this.id = String.valueOf(history.getId());
        this.loginDate = CommonUtil.toDateStr(history.getLoginDate());

        if(history.getIsLogin()){
            this.isLogin = "SUCCESS";
        }else{
            this.isLogin = "FAIL";
        }
    }
}
