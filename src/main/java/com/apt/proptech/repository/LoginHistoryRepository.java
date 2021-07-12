package com.apt.proptech.repository;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

   LoginHistory findTopByUserOrderByIdDesc(User user);

}
