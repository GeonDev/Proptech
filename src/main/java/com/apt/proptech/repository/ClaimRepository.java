package com.apt.proptech.repository;

import com.apt.proptech.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
