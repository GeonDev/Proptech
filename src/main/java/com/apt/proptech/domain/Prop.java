package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.PropType;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 *  @author : SON
 *  @since 2021. 05. 08
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : 부동산 정보를 저장한 도메인
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Prop {
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

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
