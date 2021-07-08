package com.apt.proptech.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "receiveUser")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String message;

    @OneToOne
    @JoinColumn(name = "receive_user_id")
    User receiveUser;

    LocalDateTime sandDate;
    LocalDateTime readDate;

}
