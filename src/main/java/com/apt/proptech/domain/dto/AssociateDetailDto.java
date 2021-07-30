package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.*;
import com.apt.proptech.domain.enums.PropType;
import com.apt.proptech.util.CommonUtil;
import lombok.*;

import java.util.Comparator;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssociateDetailDto {

    private String name;
    private String round;
    private String expectDate;
    private String realDate;
    private String feeRatio;
    private String city;
    private String state;
    private String address;
    private String registerDate;
    private String modifiedDate;

    //전체 참여자 수
    private int totalJoinUserCount;

    //전체 사업 금액
    private int totalRequiredPaid;

    //전체 납입 금액
    private int totalPaid;

    //전체 필요(토지구매) 금액
    private int totalPurchaseNeedPaid;

    //토지 구매에 사용한 금액
    private int totalPurchasePaid;


}
