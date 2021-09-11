package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.Account;
import com.apt.proptech.core.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(attributePaths = "user")
    List<Account> findByUserOrderByIdDesc(User user);
}
