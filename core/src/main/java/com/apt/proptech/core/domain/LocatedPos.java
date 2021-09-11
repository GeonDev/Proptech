package com.apt.proptech.core.domain;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LocatedPos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="purchase_prop_id")
    @ToString.Exclude
    private PurchaseProp prop;

    private Double xPos;
    private Double yPos;
    private Double zPos;

    private Integer orderCount;

}
