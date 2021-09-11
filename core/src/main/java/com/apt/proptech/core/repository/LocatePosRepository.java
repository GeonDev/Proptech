package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.LocatedPos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatePosRepository extends JpaRepository<LocatedPos, Long> {
}
