package com.apt.proptech.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private PurchaseProp prop;

    private Double xPos;
    private Double yPos;
    private Double zPos;

    private Integer orderCount;

}
