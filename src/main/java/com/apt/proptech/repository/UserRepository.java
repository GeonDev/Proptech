package com.apt.proptech.repository;

import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByNameContaining(String name);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByProviderAndProviderId(String provider, String providerId);

    @Query(value = "SELECT * FROM user WHERE user.user_role =:role ORDER BY id DESC" ,nativeQuery = true)
    List<User> findAllByUserRoles(@Param("role") String role);

    @Query("SELECT u FROM User u WHERE u.userState =:state ORDER BY id DESC" )
    List<User> findAllByUserState(@Param("state") UserState state , Pageable pageable);


    @Query(value = "SELECT * FROM user, company, account WHERE user.company_id = company.id AND user.id = account.user_id " , nativeQuery = true)
    List<User> findAllByPartnerInfo();

    User findByUsername(String username);
}
