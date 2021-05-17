package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
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
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String password;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserState userState;

    private Date loginDate;

    private Date retiredDate;

    @OneToMany
    private List<Account> accountList = new ArrayList<>();

    @OneToMany
    private List<AssociateUser> associateInfoList = new ArrayList<>();

    @OneToMany
    private List<Receipt> receiptList = new ArrayList<>();

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
