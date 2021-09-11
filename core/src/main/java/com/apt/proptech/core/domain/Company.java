package com.apt.proptech.core.domain;

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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ceoName;
    private String bizRegNum;
    private String address;
    private String zipCode;
    private LocalDateTime establishDate;

}
