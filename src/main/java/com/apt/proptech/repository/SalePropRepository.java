package com.apt.proptech.repository;

import com.apt.proptech.domain.SaleProp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalePropRepository extends JpaRepository<SaleProp, Long> {
}
