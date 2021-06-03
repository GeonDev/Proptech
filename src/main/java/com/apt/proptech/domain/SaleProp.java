package com.apt.proptech.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SaleProp extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String addressDetail;

    private int saleRound;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @OneToMany
    List<Claim> claimList = new ArrayList<>();

    @OneToMany
    List<OwnedHistory> ownedHistoryList = new ArrayList<>();

}
