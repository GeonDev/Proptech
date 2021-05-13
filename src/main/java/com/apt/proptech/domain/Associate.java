package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.AssociateState;
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
 *  @Description : 주택조합 정보를 저장한 도메인
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Associate extends BaseTimeEntity{

    @Id @GeneratedValue
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    AssociateState state;


    Long totalAmount;

    Long targetAmount;

    @Column(nullable = false)
    int paymentExpectCount;

    @Column(nullable = false)
    int paymentRealCount;

    Date endExpectDate;
    Date endRealDate;

    @OneToOne
    @JoinColumn(name = "prop_id")
    Prop propInfo;


    @OneToMany(mappedBy = "associateInfo")
    List<AssociateUser> userInfoList = new ArrayList<>();

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
