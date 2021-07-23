package com.apt.proptech.repository;

import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByNameContaining(String name);

    User findByUsernameAndPassword(String username, String password);

    User findByProviderAndProviderId(String provider, String providerId);

    @Query(value = "SELECT * FROM user WHERE user.user_role =:role ORDER BY id DESC" ,nativeQuery = true)
    Page<User> findAllByUserRoles(@Param("role") String role, Pageable pageable);

    @Query(value = "SELECT * FROM user WHERE user.user_state =:state ORDER BY id DESC", nativeQuery = true)
    Page<User> findAllByUserState(@Param("state") String state , Pageable pageable);

    @Query(value = "SELECT * FROM user, company, account WHERE user.company_id = company.id AND user.id = account.user_id " , nativeQuery = true)
    List<User> findAllByPartnerInfo();

    @Query("SELECT u.id FROM User u")
    List<String> findAllByUserId();

    //유저 ID로 겹칠수 없다.
    User findByUsername(String username);


    List<User> findByUserRoleAndUserStateNot(UserRole role, UserState state);

}
