package com.apt.proptech.admin.controller;

import com.apt.proptech.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class BaseController<Entity>  {

    @Autowired(required = false)
    protected BaseService<Entity> baseService;


    @PostMapping("/add")
    public Entity addItem( @RequestBody Entity entity ){
        return baseService.addItem(entity);
    }

    @GetMapping("{id}")
    public Entity getItem( @PathVariable Long id ){
        return baseService.getItem(id);
    }

    @PutMapping("")
    public Entity updateItem(@RequestBody Entity entity ){
        return baseService.updateItem(entity);
    }

    @DeleteMapping("{id}")
    public Entity deleteItem(@PathVariable Long id ){
        return baseService.deleteItem(id);
    }

}
