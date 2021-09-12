package com.apt.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PropType {
    PURCHASED("구매완료","구매한 토지 상태"),
    REQUIRED("구매중","구매가 필요한 상태");

    private String title;
    private String description;
}
