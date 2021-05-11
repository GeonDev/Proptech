package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 *
 *  @author : SON
 *  @since 2021. 05. 08
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : 사용자가 정보를 저장한 도메인
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String password;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private Date createdAt;
    private Date lastLoginAt;
    private Date updatedAt;
    private Date leaveAt;

    //private List<Account> AccountList;
    //private List<Associate> AssociateList;
    //private List<Receipt> receiptList;

}
