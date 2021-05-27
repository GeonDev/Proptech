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
public class Receipt {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "claim_id")
    private Claim claim;

    private Long payment;

    private LocalDateTime paymentDate;

    private String buyerIp;
}
