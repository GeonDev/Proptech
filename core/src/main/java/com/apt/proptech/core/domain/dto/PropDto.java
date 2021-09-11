package com.apt.proptech.core.domain.dto;

import com.apt.proptech.core.domain.ClaimProp;
import com.apt.proptech.core.domain.OwnedHistory;
import com.apt.proptech.core.domain.Receipt;
import com.apt.proptech.core.domain.SaleProp;
import com.apt.proptech.core.domain.enums.OwnedState;
import com.apt.proptech.core.util.CommonUtil;

import java.util.Comparator;
import java.util.List;

public class PropDto {

    String address;
    String owner;

    //계약일 - 계약자 입장에서
    String regDate;
    String regRound;
    String totalPrice;
    String priceRound;
    String payment;

    //미납 금
    String overdue;

    public PropDto(SaleProp saleProp){
        this.address = saleProp.getAddressDetail();
        this.regRound = String.valueOf(saleProp.getSaleRound());
        this.totalPrice = "";
        this.priceRound = "";
        this.payment = "";
        this.owner = "";
        this.regDate = "";

        OwnedHistory owned = saleProp.getOwnedHistoryList().stream()
                .filter(ownedHistory -> OwnedState.OWNED == ownedHistory.getOwnedState()
         ).max(Comparator.comparingLong(OwnedHistory::getId ) ).orElse(null);

        if(owned !=null ){
            this.owner = owned.getOwner().getName();
            this.regDate = CommonUtil.toDateStr(owned.getRegDate());
        }

        List<ClaimProp> claimList = saleProp.getClaimPropList();

        if(!claimList.isEmpty() && claimList != null){
            int tempPrice = 0;

            for(ClaimProp claim : claimList ){
                tempPrice += claim.getPayment();

                List<Receipt> receiptList = claim.getReceiptList();
                if(!receiptList.isEmpty() && receiptList != null ){
                   this.payment = String.valueOf(receiptList.stream().mapToLong(receipt-> receipt.getPayment() ).sum());
;                }
            }
            this.totalPrice = String.valueOf(tempPrice);
            this.priceRound = String.valueOf(claimList.size());

        }

        if(!this.totalPrice.equals("") && !this.payment.equals("") ){
            this.overdue =  String.valueOf( Integer.parseInt(this.totalPrice) - Integer.parseInt(this.payment));
        }else if(this.payment.equals("")  ){
            this.overdue = this.totalPrice;
        }else{
            this.overdue = "";
        }

    }
}
