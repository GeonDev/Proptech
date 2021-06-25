package com.apt.proptech.controller;

import com.apt.proptech.domain.dto.Pagination;
import com.apt.proptech.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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


    @GetMapping("")
    public Pagination getItemList(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 10) Pageable pageable,
                                  @RequestParam(value = "type", required = false, defaultValue = "") String type,
                                  @RequestParam(value = "value", required = false, defaultValue = "") String value ){

        return baseService.getItemList(pageable, type, value);
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
