package com.apt.proptech.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER("유저","일반 사용자"),
    STAFF("스탭","운영자"),
    ADMIN("통합관리자","시스템 통합 관리자"),
    MANAGER("관리자"," 운영 관리자"),
    PARTNER("파트너사","시공사 정보")
    ;

    private String title;
    private String description;
}
