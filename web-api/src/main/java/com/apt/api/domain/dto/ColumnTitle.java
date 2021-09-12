package com.apt.api.domain.dto;


import lombok.Getter;

//테이블 칼럼명, 순서를 기록하는 클래스

@Getter
public class ColumnTitle {

    private String title;
    private String order;

    public ColumnTitle(String title, String order ){
        this.title = title;
        this.order = order;
    }
}
