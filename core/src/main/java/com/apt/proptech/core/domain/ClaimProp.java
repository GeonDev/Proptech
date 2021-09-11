package com.apt.proptech.core.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ClaimProp extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long payment;

    //1차 납입인지, 2차 납입인지 구분
    private int round;

    private String description;

    @ManyToOne
    @JoinColumn(name ="sale_prop_id")
    @ToString.Exclude
    private SaleProp saleProp;

    @OneToMany(mappedBy = "claimProp" ,fetch = FetchType.LAZY)
    private List<Receipt> receiptList = new ArrayList<>();
}
