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
    private List<PurchaseProp> purchasePropList = new ArrayList<>();

    @OneToMany
    private List<SaleProp> salePropList = new ArrayList<>();

    @OneToMany
    private List<Staff> staffList = new ArrayList<>();

    //전체 참여자 수
    @Transient
    private int totalJoinUserCount;

    //전체 사업 금액
    @Transient
    private int totalRequiredPaid;

    //전체 미납 금액
    @Transient
    private int totalUnPaid;


}
