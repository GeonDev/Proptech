package com.apt.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssociateRound {
    CREATE("생성","신규 생성된 조합", 1),
    SIGNED("등록","인가된 조합", 2),
    SELECTION("선정","시공사가 선정된 조합",3),
    COMPLETION("준공","공사가 시작된 조합",4),
    SALE("분양","주택 분양 중인 조합",5),
    FINISH("종료","종료된 조합",6);


    private String title;
    private String description;
    private int order;

}
