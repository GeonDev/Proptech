package com.apt.proptech.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Claim extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long payment;

    private int round;

    private String description;

    @ManyToOne
    @JoinColumn(name ="sale_prop_id")
    private SaleProp saleProp;


    @OneToMany
    private List<Receipt> receiptList = new ArrayList<>();

}
