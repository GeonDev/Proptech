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

    //컬럼 이름 정보 - > 일부 컬럼을 지울떄 사용
    private List<ColumnTitle> columnTitles;

    //칼럼의 총 개수 -> 선탯되지 않은 칼럼을 찾을때 사영
    private Integer totalColumnCount;

    //콘텐츠 정보
    private List<Entity> contents;

}

