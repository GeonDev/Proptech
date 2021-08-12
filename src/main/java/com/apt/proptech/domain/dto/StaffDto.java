package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.Staff;
import com.apt.proptech.util.CommonUtil;

public class StaffDto {

    private String name;
    private String role;
    private String phoneNumber;
    private String regDate;

    public StaffDto(Staff staff){

        this.name = staff.getUser().getName();

        this.role = staff.getStaffRole().getTitle();

        this.phoneNumber = CommonUtil.null2str(staff.getUser().getPhoneNumber());

        this.regDate = CommonUtil.toDateStr(staff.getRegDate());
    }

}
