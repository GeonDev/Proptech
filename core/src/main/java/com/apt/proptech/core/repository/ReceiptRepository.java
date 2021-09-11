package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {


    @Query(value = "SELECT SUM(r.payment) FROM Receipt r")
    public Long getTotalReceiptPaid();

}
