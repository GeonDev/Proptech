package com.apt.proptech.Controller;

import com.apt.proptech.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class BaseController<Entity>  {

    @Autowired(required = false)
    protected BaseService<Entity> baseService;


    @PostMapping("")
    public Entity create( @RequestBody Entity entity ){
        return baseService.addItem(entity);
    }

    @GetMapping("{id}")
    public Entity read( @PathVariable Long id ){
        return baseService.getItem(id);
    }

    @PutMapping("")
    public Entity update(@RequestBody Entity entity ){
        return baseService.updateItem(entity);
    }

    @DeleteMapping("{id}")
    public Entity delete(@PathVariable Long id ){
        return baseService.deleteItem(id);
    }

}
