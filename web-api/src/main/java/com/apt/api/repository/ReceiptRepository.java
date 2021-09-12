package com.apt.api.repository;


import com.apt.api.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {


    @Query(value = "SELECT SUM(r.payment) FROM Receipt r")
    public Long getTotalReceiptPaid();

}
