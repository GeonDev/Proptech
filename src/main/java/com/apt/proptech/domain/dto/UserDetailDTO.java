package com.apt.proptech.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDTO {

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

    private String lastLoginDate;


}
