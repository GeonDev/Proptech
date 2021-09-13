package com.apt.api.service;


import com.apt.api.domain.Account;
import com.apt.api.domain.User;
import com.apt.api.domain.dto.AccountDto;
import com.apt.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Cacheable(value = "account", key = "#user")
    public List<AccountDto> getAccountInfo(User user){
        List<AccountDto> list = new ArrayList<>();

        List<Account> temp = accountRepository.findByUserOrderByIdDesc(user);

        if(temp != null && !temp.isEmpty() ){
            for( Account account : temp){
                list.add(new AccountDto( account) );
            }
        }

        return list;
    }
}
