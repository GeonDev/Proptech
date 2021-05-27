package com.apt.proptech.repository;

import com.apt.proptech.domain.PurchaseProp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasePropRepository extends JpaRepository<PurchaseProp, Long> {
}
