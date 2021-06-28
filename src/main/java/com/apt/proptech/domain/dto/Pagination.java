package com.apt.proptech.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination<Entity>{

    private Integer totalPages;
    private Long totalElements;

    private Integer pageSize;

    private Integer currentPage;
    private Integer currentElements;

    //첫번째 페이지 여부
    private boolean isFirstPage;

    //마지막 페이지 여부
    private boolean isLastPage;

    //화면에 표시할 페이지 번호
    private List<Integer> pageNumbers;

    //이전번호 - 머스테지는 연산이 안되서 추가
    private Integer prePageNum;

    //이전번호 - 머스테지는 연산이 안되서 추가
    private  Integer nextPageNum;


    //검색 타입
    private List<String> searchType;

    //콘텐츠 정보
    private List<Entity> contents;

}

