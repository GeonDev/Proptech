package com.apt.proptech.repository;


import com.apt.proptech.domain.OwnedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedHistoryRepository extends JpaRepository<OwnedHistory, Long> {
}
