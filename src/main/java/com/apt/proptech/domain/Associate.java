package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.AssociateRound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class Associate extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AssociateRound associateRound;

    private Double operateFeeRatio;

    private LocalDateTime endExpectDate;

    private LocalDateTime endRealDate;

    private String city;

    private String state;

    private String address;

    @OneToMany(mappedBy = "associateInfo",fetch = FetchType.LAZY)
    private List<PurchaseProp> purchasePropList = new ArrayList<>();

    @OneToMany(mappedBy ="associate" ,fetch = FetchType.LAZY)
    private List<SaleProp> salePropList = new ArrayList<>();

    @OneToMany(mappedBy ="associate" ,fetch = FetchType.LAZY )
    private List<Staff> staffList = new ArrayList<>();


}
