package com.apt.proptech.repository;

import com.apt.proptech.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
