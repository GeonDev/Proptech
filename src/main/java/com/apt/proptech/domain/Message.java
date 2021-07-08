package com.apt.proptech.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = {"sendUser, receiveUser"})
public class Message {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String message;

    @OneToOne
    @JoinColumn(name = "sand_user_id")
    User sendUser;

    @OneToOne
    @JoinColumn(name = "receive_user_id")
    User receiveUser;

    LocalDateTime sandDate;
    LocalDateTime readDate;
}
