package com.apt.api.service;


import com.apt.api.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;


    public Long getTotalPaid(){
         Long totalPaid =  receiptRepository.getTotalReceiptPaid();

         if(totalPaid!= null){
             return totalPaid;
         }

        return 0l;
    }


}
