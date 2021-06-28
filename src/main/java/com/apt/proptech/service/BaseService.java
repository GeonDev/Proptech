package com.apt.proptech.service;


import com.apt.proptech.domain.dto.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class BaseService<Entity> {

    @Autowired(required = false)
    protected JpaRepository<Entity,Long> baseRepository;

    public abstract Entity addItem(Entity object );

    public abstract Entity getItem(Long id );

    public abstract Entity updateItem(Entity object );

    public abstract Entity deleteItem(Long id );

    // type : 검색 조건  value : 검색 값(입력값)
    public abstract Pagination getItemList(Pageable pageable,String type, String value);


    public int setNextPageNum(int currentPage, boolean isLast ){

        if(!isLast ){
            return currentPage + 2;
        }
        return -1;
    }

    public int setPrePageNum(int currentPage, boolean isFirst ){

        if( !isFirst ){
            return currentPage-1;
        }

        return  -1;
    }


    public List<Integer> setPageNumber(int currentPage, int pageSize, int totalPages ){

        List<Integer> pageNum = new ArrayList<>();

        int size = pageSize/2;

        int start = currentPage- size;
        if(start < 0){
            start = 0;
        }

        int end = currentPage + size;
        if( end > totalPages){
            end = totalPages;
        }

        for(int i = start; i< currentPage; i++){
            pageNum.add(i+1);
        }
        for(int i = currentPage; i< end; i++){
            pageNum.add(i+1);
        }

        return  pageNum;
    }
}
