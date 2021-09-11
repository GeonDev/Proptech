package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.ClaimProp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<ClaimProp, Long> {
}
