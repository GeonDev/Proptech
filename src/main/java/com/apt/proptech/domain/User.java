package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String password;

    private String name;
    private String email;

    private String profileImg;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserState userState;

    private LocalDateTime retiredDate;

    private LocalDateTime modiPasswordDate;

    @OneToMany
    private List<Account> accountList = new ArrayList<>();

    @OneToMany
    private List<LoginHistory> loginHistoryList = new ArrayList<>();




}
