package com.apt.proptech.core.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String message;

    @ManyToOne
    @JoinColumn(name = "receive_user_id")
    @ToString.Exclude
    User receiveUser;

    LocalDateTime sandDate;
    LocalDateTime readDate;

}
