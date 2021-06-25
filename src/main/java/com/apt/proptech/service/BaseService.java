package com.apt.proptech.service;


import com.apt.proptech.domain.dto.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

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
}
