package com.apt.proptech.service;


import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.dto.Pagination;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssociateService extends BaseService<Associate>{
    @Override
    public Associate addItem(Associate object) {
        return null;
    }

    @Override
    public Associate getItem(Long id) {
        return null;
    }

    @Override
    public Associate updateItem(Associate object) {
        return null;
    }

    @Override
    public Associate deleteItem(Long id) {
        return null;
    }

    @Override
    public Pagination getItemList(Pageable pageable, String type, String value) {
        return null;
    }
}
