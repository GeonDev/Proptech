package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.AccountState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 *
 *  @author : SON
 *  @since 2021. 05. 08
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : 사용자가 저장한 은행계좌 정보 도메인
 *
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    Long Id;
    String name;
    String account;

    AccountState state;

    Date regDate;
    Date updateDate;
    Date lastUseDate;



}
