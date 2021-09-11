package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.User;
import com.apt.proptech.core.domain.enums.UserRole;
import com.apt.proptech.core.domain.enums.UserState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByProviderAndProviderId(String provider, String providerId);

    User findByUsername(String username);

    List<User> findByUserRoleAndUserStateNot(UserRole role, UserState state);

    @Query(value = "SELECT * FROM user WHERE user.user_role =:role ORDER BY id DESC" ,nativeQuery = true)
    Page<User> findAllByUserRoles(@Param("role") String role, Pageable pageable);

    @Query(value = "SELECT * FROM user WHERE user.user_state =:state ORDER BY id DESC", nativeQuery = true)
    Page<User> findAllByUserState(@Param("state") String state , Pageable pageable);

}
