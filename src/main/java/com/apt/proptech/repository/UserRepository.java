package com.apt.proptech.repository;

import com.apt.proptech.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByNameContaining(String name);

    Optional<User> findByUserNameAndPassword(String userName, String password);
}
