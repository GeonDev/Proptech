package com.apt.proptech.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
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
    Long id;

    String city;
    String State;
    String zipCode;

    String address;
    String addressDetail;


    @OneToOne(mappedBy = "propInfo")
    Associate associateInfo;

    @OneToMany
    List<LocatePos> locatePosList;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
