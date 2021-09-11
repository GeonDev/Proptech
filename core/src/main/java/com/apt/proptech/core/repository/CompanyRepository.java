package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
