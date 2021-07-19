package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import lombok.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
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

    //OAuth를 위한 필드
    private String provider;
    private String providerId;


    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserState userState;

    private LocalDateTime retiredDate;

    private LocalDateTime modiPasswordDate;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @ToString.Exclude
    private Company company;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<LoginHistory> loginHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<OwnedHistory> ownedHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Receipt> receiptList = new ArrayList<>();

}
