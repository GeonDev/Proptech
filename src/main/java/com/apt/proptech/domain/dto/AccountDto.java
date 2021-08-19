package com.apt.proptech.domain.dto;


import com.apt.proptech.domain.Account;
import com.apt.proptech.util.CommonUtil;
import lombok.Getter;

@Getter
public class AccountDto {

    private Long id;

    private String account;

    private String bankName;

    private String accountState;

    private String useDate;

    public AccountDto(Account account ){
        this.id = account.getId();

        this.account = account.getAccount();

        this.bankName = account.getBankName();

        this.accountState = account.getAccountState().getTitle();

        this.useDate = CommonUtil.toDateStr(account.getUseDate() );
    }
}
