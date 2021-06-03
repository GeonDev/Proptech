package com.apt.proptech.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StaffRole {
    STAFF("스탭", "일반 스탭"),
    MANAGER("관리자","관리 책임자") ;

    private String title;
    private String description;
}
