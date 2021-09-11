package com.apt.proptech.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReceiptState {
    PAID("결제","해당금액이 지불된 상태"),
    REFUNDED("환불", "금액환불");


    private String title;
    private String description;
}
