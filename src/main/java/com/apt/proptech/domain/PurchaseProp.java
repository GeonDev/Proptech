package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.PropType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PurchaseProp extends BaseTimeEntity{
    @Id @GeneratedValue
    private Long id;

    private String city;
    private String state;
    private String zipCode;

    private String address;
    private String addressDetail;

    private Long price;

    private LocalDateTime purchaseDate;

    @Enumerated(EnumType.STRING)
    private PropType propType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associate_id")
    private Associate associateInfo;

    @OneToMany
    private List<LocatedPos> locatedList = new ArrayList<>();

    @OneToMany
    private List<PropPrice> priceList = new ArrayList<>();;

}
