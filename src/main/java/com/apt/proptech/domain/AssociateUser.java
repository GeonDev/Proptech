package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.UserGrade;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;


/**
 *
 *  @author : SON
 *  @since 2021. 05. 08
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : User와 Associate의 many to many 관계를 저장하기 위한 도메인
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AssociateUser extends BaseTimeEntity{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="associate_id")
    private Associate associateInfo;

    @Enumerated(EnumType.STRING)
    private UserGrade userGrade;

    private String propAdress;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
