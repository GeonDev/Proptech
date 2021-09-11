package com.apt.proptech.core.domain;

import com.apt.proptech.core.domain.enums.IpChecked;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
public class LoginHistory extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime loginDate;

    private String ip;

    //IP 상태 체크
    @Enumerated(EnumType.STRING)
    private IpChecked ipChecked = IpChecked.UNCHECKED;

    //로그인 성공 여부
    private Boolean isLogin;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
