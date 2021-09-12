package com.apt.api.repository;


import com.apt.api.domain.LocatedPos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatePosRepository extends JpaRepository<LocatedPos, Long> {
}
