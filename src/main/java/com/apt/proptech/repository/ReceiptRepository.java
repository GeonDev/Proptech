package com.apt.proptech.repository;

import com.apt.proptech.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
