package com.apt.proptech.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 *
 *  @author : SON
 *  @since 2021. 05. 08
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : 유저가 모임에 제출한 비용을 기록해 놓은 도메인
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Receipt {

    Long id;

    User user;

    Associate associate;

    Long payment;

    Date paymentAt;

    Date expectAt;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
