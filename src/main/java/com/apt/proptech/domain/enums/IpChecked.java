package com.apt.proptech.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IpChecked {
    UNCHECKED("미확인","신규, 미승인 IP"),
    BANNED("금지","접속 금지된 IP"),
    CHECKED("확인","유저 승인 IP");

    private String title;
    private String description;
}
