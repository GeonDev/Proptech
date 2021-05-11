package com.apt.proptech.domain;

import com.apt.proptech.domain.enums.UserLevel;

import javax.xml.crypto.Data;

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
public class AssociateUser {
    Long id;

    User user;
    Associate associate;

    UserLevel level;

    Data createAt;
    Data updateAt;



}
