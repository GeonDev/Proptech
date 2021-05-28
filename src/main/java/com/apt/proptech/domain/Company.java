package com.apt.proptech.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;
    private String bizRegNum;
    private String address;
    private String zipCode;
    private LocalDateTime establishDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
