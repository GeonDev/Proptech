package com.apt.proptech.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "associate")
public class SaleProp extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressDetail;

    private int saleRound;

    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @OneToMany(mappedBy = "saleProp", fetch = FetchType.LAZY)
    List<ClaimProp> claimPropList = new ArrayList<>();

    @OneToMany(mappedBy = "saleProp", fetch = FetchType.LAZY)
    List<OwnedHistory> ownedHistoryList = new ArrayList<>();

}
