package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.AccountState;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

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
@Entity
@ToString(exclude = "user")
public class Account extends BaseTimeEntity{

    @Id @GeneratedValue
    Long Id;

    String nickName;

    String account;

    String bankName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    User user;

    @Enumerated(EnumType.STRING)
    AccountState accountState;

    LocalDateTime useDate;


    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
