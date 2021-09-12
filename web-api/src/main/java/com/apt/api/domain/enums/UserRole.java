package com.apt.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ROLE_USER("유저","일반 사용자"),
    ROLE_STAFF("스탭","운영자"),
    ROLE_ADMIN("통합관리자","시스템 통합 관리자"),
    ROLE_MANAGER("관리자"," 운영 관리자"),
    ROLE_PARTNER("파트너사","시공사 정보")
    ;

    private String title;
    private String description;
}
