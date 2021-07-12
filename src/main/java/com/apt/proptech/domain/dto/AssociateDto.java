package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.Claim;
import com.apt.proptech.domain.Receipt;
import com.apt.proptech.domain.SaleProp;
import com.apt.proptech.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    //전체 미납 금액
    private int totalUnPaid;


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


  /*      //전체 사업 금액
        for (SaleProp sale : associate.getSalePropList()){
            for(Claim claim : sale.getClaimList()){
                this.totalRequiredPaid += claim.getPayment();
            }
        }

        //전체 납입 금액
        for (SaleProp sale : associate.getSalePropList()){
            for(Claim claim : sale.getClaimList()){
               for(Receipt receipt : claim.getReceiptList()){
                   this.totalPaid += receipt.getPayment();
               }
            }
        }

        //전체 미납금액
        this.totalUnPaid =  this.totalRequiredPaid -  this.totalPaid;
  */
    }
}
