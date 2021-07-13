package com.apt.proptech.repository;

import com.apt.proptech.domain.ClaimProp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<ClaimProp, Long> {
}
