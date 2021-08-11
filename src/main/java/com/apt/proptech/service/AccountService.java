package com.apt.proptech.service;

import com.apt.proptech.domain.Account;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.dto.AccountDto;
import com.apt.proptech.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

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
