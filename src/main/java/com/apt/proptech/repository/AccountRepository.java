package com.apt.proptech.repository;

import com.apt.proptech.domain.Account;
import com.apt.proptech.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
