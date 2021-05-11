package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.AssociateState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 *
 *  @author : SON
 *  @since 2021. 05. 08
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : 주택조합 정보를 저장한 도메인
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Associate {
    Long id;
    String name;
    AssociateState state;

    Long totalAmount;

    Long targetAmount;

    Date createAt;

    Date endExpectAt;
    Date endAt;


}
