package com.apt.proptech.repository;

import com.apt.proptech.domain.LocatedPos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatePosRepository extends JpaRepository<LocatedPos, Long> {
}
