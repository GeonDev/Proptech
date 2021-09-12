package com.apt.api.repository;


import com.apt.api.domain.ClaimProp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<ClaimProp, Long> {
}
