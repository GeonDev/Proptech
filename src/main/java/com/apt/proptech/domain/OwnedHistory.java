package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.OwnedState;
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
public class OwnedHistory extends BaseTimeEntity{

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private OwnedState ownedState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_prop_id")
    private SaleProp prop;

}
