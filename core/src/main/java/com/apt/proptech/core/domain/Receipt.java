package com.apt.proptech.core.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Receipt extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long payment;

    private String buyerIp;

    @ManyToOne
    @JoinColumn(name = "claim_id")
    @ToString.Exclude
    private ClaimProp claimProp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
