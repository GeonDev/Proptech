package com.apt.proptech.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *
 *  @author : SON
 *  @since 2021. 05. 19
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : 모임에서 선정한 지불 정보를 저장히는 곳
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Claim extends BaseTimeEntity{

    @Id @GeneratedValue
    private Long id;

    private Long payment;

    private int count;

    @ManyToOne
    @JoinColumn(name ="associate_id")
    private Associate associateInfo;

}
