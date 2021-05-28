package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.AssociateState;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
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
    private AssociateState associateState;

    private Double operateFeeRatio;

    private LocalDateTime endExpectDate;

    private LocalDateTime endRealDate;

    private String city;

    private String state;

    private String address;


    @OneToMany
    List<PurchaseProp> purchasePropList = new ArrayList<>();

    @OneToMany
    List<SaleProp> salePropList = new ArrayList<>();




}
