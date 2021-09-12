package com.apt.api.domain.dto;

import com.apt.api.domain.*;
import com.apt.api.domain.enums.PropType;
import com.apt.api.util.CommonUtil;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociateDto {

    private Long id;
    private String name;
    private String logo;
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

    //필요금액 대비 납입금
    private int perPaid;
    
    
    //토지 구매에 사용한 금액
    private int totalPurchasePaid;

    public AssociateDto(Associate associate){
        this.id = associate.getId();
        this.name = associate.getName();
        this.logo = associate.getLogo();
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
        this.perPaid = 0;

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

        if(this.totalRequiredPaid !=0 && this.totalPaid !=0){
            //전체 금액 대비 납입금 상황 (퍼센트)
            this.perPaid = (int) Math.round( ((double)this.totalPaid/(double)this.totalRequiredPaid )* 100.0f );
        }


        //전체 필요(토지 구매) 금액
        for(PurchaseProp prod : associate.getPurchasePropList()){
            if(prod !=null ){
                List<PropPrice> priceList = prod.getPriceList();
                PropPrice price = priceList.stream().max(Comparator.comparing(PropPrice::getId).reversed()).orElse(null);

                if(price != null){
                    this.totalPurchaseNeedPaid += price.getPrice();

                    //토지 구매에 사용한 금액
                    if(prod.getPropType() == PropType.PURCHASED ){
                        this.totalPurchasePaid += price.getPrice();
                    }
                }
            }
        }
    }
}
