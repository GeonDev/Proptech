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
@ToString(exclude = "associate")
public class PurchaseProp extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String state;
    private String zipCode;

    private String address;
    private String addressDetail;

    private LocalDateTime purchaseDate;

    @Enumerated(EnumType.STRING)
    private PropType propType;

    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @OneToMany(mappedBy = "prop", fetch = FetchType.LAZY)
    private List<LocatedPos> locatedList = new ArrayList<>();

    @OneToMany(mappedBy = "prop", fetch = FetchType.LAZY)
    private List<PropPrice> priceList = new ArrayList<>();;

}
