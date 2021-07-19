package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.*;
import com.apt.proptech.domain.enums.PropType;
import com.apt.proptech.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociateDto {

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

    public AssociateDto(Associate associate){
        this.name = associate.getName();
        this.round = associate.getAssociateRound().name();
        this.expectDate = CommonUtil.toDateStr(associate.getEndExpectDate());
        this.realDate = CommonUtil.toDateStr(associate.getEndRealDate());
        this.feeRatio = CommonUtil.null2str(associate.getOperateFeeRatio());
        this.city = CommonUtil.null2str(associate.getCity());
        this.state = CommonUtil.null2str(associate.getState());
        this.address = CommonUtil.null2str(associate.getState());
        this.registerDate = CommonUtil.toDateStr(associate.getRegDate());
        this.modifiedDate = CommonUtil.toDateStr(associate.getModiDate());

        this.totalJoinUserCount = 0;
        this.totalRequiredPaid = 0;
        this.totalPaid = 0;
  

        //전체 사업 금액
        for (SaleProp sale : associate.getSalePropList()){
            if(sale !=null ){
                for(ClaimProp claim : sale.getClaimPropList()){
                    if(claim!= null ){
                        this.totalRequiredPaid += claim.getPayment();
                    }
                }
            }
        }

        //전체 납입 금액
        for (SaleProp sale : associate.getSalePropList()){
            if(sale !=null ){
                for(ClaimProp claim : sale.getClaimPropList()){
                    if(claim !=null ){
                        for(Receipt receipt : claim.getReceiptList()){
                            if(receipt !=null ){
                                this.totalPaid += receipt.getPayment();
                            }
                        }
                    }
                }
            }

        }


        //전체 필요(토지 구매) 금액
        for(PurchaseProp prod : associate.getPurchasePropList()){
            if(prod !=null ){
                Collections.sort(prod.getPriceList(), (o1, o2) -> { return (int)(o2.getId() - o1.getId()); } );
                this.totalPurchaseNeedPaid += prod.getPriceList().get(0).getPrice();
            }
        }

        //토지 구매에 사용한 금액
        for(PurchaseProp prod : associate.getPurchasePropList()){
            if(prod !=null ){
                Collections.sort(prod.getPriceList(), (o1, o2) -> { return (int)(o2.getId() - o1.getId()); } );
                if(prod.getPropType() == PropType.PURCHASED ){
                    this.totalPurchasePaid += prod.getPriceList().get(0).getPrice();
                }
            }
        }


    }
}
