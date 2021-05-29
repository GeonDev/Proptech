package com.apt.proptech.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OwnedState {

    OWNED("보유한"," 현재 부동산을 소유"),
    Abandoned("포기한","해당 부동산을 팔음");

    private String title;
    private String description;

}
