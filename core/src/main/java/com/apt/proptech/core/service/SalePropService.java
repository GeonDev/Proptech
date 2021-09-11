package com.apt.proptech.core.service;

import com.apt.proptech.core.domain.Associate;
import com.apt.proptech.core.domain.SaleProp;
import com.apt.proptech.core.domain.dto.PropDto;
import com.apt.proptech.core.repository.SalePropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalePropService {

    @Autowired
    private SalePropRepository salePropRepository;

    public List<PropDto> getPropList(Associate associate){
        List<PropDto> list = new ArrayList<>();

        for (SaleProp saleProp : salePropRepository.findByAssociate(associate)) {
            list.add(new PropDto(saleProp));
        }

        return  list;
    }

}
