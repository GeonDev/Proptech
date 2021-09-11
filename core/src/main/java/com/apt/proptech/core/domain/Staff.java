package com.apt.proptech.core.domain;

import com.apt.proptech.core.domain.enums.StaffRole;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Staff extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StaffRole staffRole = StaffRole.STAFF;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associate_id")
    @ToString.Exclude
    private Associate associate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

}
