package com.apt.proptech.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Receipt extends BaseTimeEntity{

    @Id @GeneratedValue
    private Long id;

    private Long payment;

    private String buyerIp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id")
    private Claim claim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
