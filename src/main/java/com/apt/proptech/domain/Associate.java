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
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AssociateState state;


    @Column(nullable = false)
    private int operateFeeRatio;

    @Column(nullable = false)
    private int paymentExpectCount;

    @Column(nullable = false)
    private int paymentRealCount;

    private Long totalRequiredPayment;


    private Date endExpectDate;
    private Date endRealDate;

    @OneToOne
    @JoinColumn(name = "prop_id")
    Prop propInfo;


    @OneToMany(mappedBy = "associateInfo")
    List<AssociateUser> userInfoList = new ArrayList<>();

    @OneToMany
    List<Claim> claimListList = new ArrayList<>();



    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
