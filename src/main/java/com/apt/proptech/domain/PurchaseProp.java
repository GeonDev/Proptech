package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.PropType;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Associate associateInfo;

    @OneToMany
    private List<LocatePos> locatePosList;

    @OneToMany
    private List<PropPrice> propPriceList;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
