package com.apt.proptech.service;

import com.apt.proptech.domain.LoginIp;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.dto.LoginIpDto;
import com.apt.proptech.repository.LoginIpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginIpService {

    @Autowired
    private LoginIpRepository loginIpRepository;

    public List<LoginIpDto> getLoginIpInfo(User user){
        List<LoginIpDto> list = new ArrayList<>();

        List<LoginIp> temp = loginIpRepository.findTop10ByUserOrderByIdDesc(user);

        if(temp !=null && !temp.isEmpty() ){
            for( LoginIp ip : temp ){
                list.add( new LoginIpDto(ip));
            }
        }

        return list;
    }
}
