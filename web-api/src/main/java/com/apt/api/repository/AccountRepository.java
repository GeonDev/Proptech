package com.apt.api.repository;


import com.apt.api.domain.Account;
import com.apt.api.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(attributePaths = "user")
    List<Account> findByUserOrderByIdDesc(User user);
}
