package com.apt.proptech.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SaleProp extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;


    private String addressDetail;

    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "claim_id")
    private Claim claim;

}
