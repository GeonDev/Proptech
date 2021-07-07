package com.apt.proptech.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssociateRound {
    INACTIVE("종료","종료된 조합"),
    SIGNED("등록","인가된 조합"),
    CREATE("생성","신규 생성된 조합"),
    SELECTION("선정","시공사가 선정된 조합"),
    COMPLETION("준공","공사가 시작된 조합"),
    SALE("분양","주택 분양 중인 조합");

    private String title;
    private String description;

}
