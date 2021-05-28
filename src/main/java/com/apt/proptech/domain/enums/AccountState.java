package com.apt.proptech.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountState {
    ACTIVE("사용중"," 활성 계좌 정보"),
    INACTIVE("미사용","휴면 계좌 정보");

    private String title;
    private String description;
}
