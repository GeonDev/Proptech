package com.apt.proptech.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "saleProp")
public class ClaimProp extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long payment;

    private int round;

    private String description;

    @ManyToOne
    @JoinColumn(name ="sale_prop_id")
    private SaleProp saleProp;

    @OneToMany(mappedBy = "claimProp" ,fetch = FetchType.LAZY)
    private List<Receipt> receiptList = new ArrayList<>();
}