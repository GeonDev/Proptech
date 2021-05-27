package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.AccountState;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "user")
public class Account extends BaseTimeEntity{

    @Id @GeneratedValue
    private Long Id;

    private String nickName;

    private String account;

    private String bankName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private AccountState accountState;

    private LocalDateTime useDate;

}
