package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.UserRole;
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
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String password;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private Date regDate;
    private Date loginDate;
    private Date updatedDate;
    private Date removeDate;

    @OneToMany(mappedBy = "user")
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    private List<AssociateUser> associateInfoList = new ArrayList<>();

    //private List<Receipt> receiptList;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
