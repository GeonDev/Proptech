package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.AccountState;
import lombok.*;

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

    @Id
    private Long id;

    private String description;

    private String account;

    private String bankName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private AccountState accountState;

    private LocalDateTime useDate;

}
