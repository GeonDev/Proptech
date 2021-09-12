package com.apt.api.repository;



import com.apt.api.domain.OwnedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedHistoryRepository extends JpaRepository<OwnedHistory, Long> {
}
