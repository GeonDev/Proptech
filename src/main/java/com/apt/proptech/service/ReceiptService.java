package com.apt.proptech.service;

import com.apt.proptech.repository.ReceiptRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;


    public Long getTotalPaid(){
        return  receiptRepository.getTotalReceiptPaid();
    }


}
