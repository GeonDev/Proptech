package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.IpChecked;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LoginHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime loginDate;

    private String loginIp;

    private Boolean isLogin;

    @Enumerated(EnumType.STRING)
    private IpChecked ipChecked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
