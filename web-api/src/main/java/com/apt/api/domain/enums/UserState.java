package com.apt.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserState {
    UN_AUTH("미인증","본인인증하지 않은 회원"),
    AUTH("인증","본인인증 회원"),
    RETIRED("탈퇴","탈퇴한 회원");

    private String title;
    private String description;
}
