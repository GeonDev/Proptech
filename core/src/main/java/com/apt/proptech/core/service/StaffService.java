package com.apt.proptech.core.service;

import com.apt.proptech.core.domain.Associate;
import com.apt.proptech.core.domain.dto.StaffDto;
import com.apt.proptech.core.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<StaffDto> getStaffList(Associate associate){
        List<StaffDto> list = new ArrayList<>();

        staffRepository.findByAssociate(associate).stream().forEach(staff -> {
            list.add(new StaffDto(staff) );
        });

        return list;
    }
}
