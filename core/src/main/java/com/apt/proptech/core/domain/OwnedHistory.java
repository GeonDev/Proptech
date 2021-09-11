package com.apt.proptech.core.domain;

import com.apt.proptech.core.domain.enums.OwnedState;
import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OwnedHistory extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OwnedState ownedState = OwnedState.OWNED;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User owner;

    @ManyToOne
    @JoinColumn(name = "sale_prop_id")
    @ToString.Exclude
    private SaleProp saleProp;

}
