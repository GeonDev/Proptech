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
    private Integer currentPage;
    private Integer currentElements;

    private List<Entity> contents;


}

