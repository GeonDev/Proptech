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

    //첫번째 페이지
    private boolean isFirstPage;
    //마지막 페이지
    private boolean isLastPage;

    private List<Integer> pageNumbers;

    private List<Entity> contents;

}

