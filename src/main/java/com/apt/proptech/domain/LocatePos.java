package com.apt.proptech.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 *
 *  @author : SON
 *  @since 2021. 05. 08
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : 지도에 영역을 표시하기위해 사용하는 도메인
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LocatePos {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="purchase_prop_id")
    private PurchaseProp prop;

    private Double xPos;
    private Double yPos;
    private Double zPos;

    private int order;

}
