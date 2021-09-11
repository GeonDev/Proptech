package com.apt.proptech.core.repository;


import com.apt.proptech.core.domain.OwnedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedHistoryRepository extends JpaRepository<OwnedHistory, Long> {
}
