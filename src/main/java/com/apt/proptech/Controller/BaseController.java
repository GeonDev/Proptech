package com.apt.proptech.Controller;

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


    @PostMapping("")
    public Entity addItem( @RequestBody Entity entity ){
        return baseService.addItem(entity);
    }

    @GetMapping("{id}")
    public Entity getItem( @PathVariable Long id ){
        return baseService.getItem(id);
    }


    @GetMapping("")
    public Pagination getItemList(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 10) Pageable pageable){
        return baseService.getItemList(pageable);
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
