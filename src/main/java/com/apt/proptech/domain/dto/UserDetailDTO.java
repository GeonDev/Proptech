package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.Account;
import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.LoginIp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDto {

    private String id;
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

    private List<LoginHistory> loginHistoryList;

    private List<LoginIp> loginIpList;

    private List<Account> accountList;

}
