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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //다른 엔티티의 user_id와 혼동하지 말것! -> 로그인시 구분되기 쉽게 하는 이름
    //security를 사용할때 기본으로 요구하는 값
    private String username;

    private String password;

    //실명 입력
    private String name;
    private String email;

    private String profileImg;

    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserState userState;

    private LocalDateTime retiredDate;

    private LocalDateTime modiPasswordDate;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "user")
    private List<Account> accountList = new ArrayList<>();

    @OneToMany
    private List<LoginHistory> loginHistoryList = new ArrayList<>();

    @OneToMany
    private List<OwnedHistory> ownedHistoryList = new ArrayList<>();

    @OneToMany
    private List<Receipt> receiptList = new ArrayList<>();

}
