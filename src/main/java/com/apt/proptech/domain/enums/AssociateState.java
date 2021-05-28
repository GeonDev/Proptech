package com.apt.proptech.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssociateState {
    INACTIVE("종료","종료된 조합"),
    ACTIVE("등록","인가된 조합"),
    CREATE("생성","신규 생성된 조합");

    private String title;
    private String description;

}
