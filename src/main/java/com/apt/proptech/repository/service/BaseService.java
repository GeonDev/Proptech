package com.apt.proptech.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
