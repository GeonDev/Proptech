package com.apt.proptech.domain;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 *
 *  @author : SON
 *  @since 2021. 05. 08
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : 단계별 목표금액을 지정하고 데이터를 저장하는 곳
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagetMoney {

    Long id;
    int order;

    User user;

    Long tagetValue;
    Long sumValue;





}
