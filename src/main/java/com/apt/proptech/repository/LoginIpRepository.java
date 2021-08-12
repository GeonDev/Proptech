package com.apt.proptech.repository;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.LoginIp;
import com.apt.proptech.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


public interface LoginIpRepository extends JpaRepository<LoginIp, Long> {

    LoginIp findByUserAndIp(User user, String ip );

    @Transactional
    List<LoginIp> findTop10ByUserOrderByIdDesc(User user);



}
