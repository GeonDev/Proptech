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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String message;

    @ManyToOne
    @JoinColumn(name = "sand_user_id")
    User sendUser;

    @ManyToOne
    @JoinColumn(name = "receive_user_id")
    User receiveUser;

    LocalDateTime sandDate;
    LocalDateTime readDate;
}
