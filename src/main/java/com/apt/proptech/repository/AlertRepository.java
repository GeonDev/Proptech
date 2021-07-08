package com.apt.proptech.repository;

import com.apt.proptech.domain.Alert;
import com.apt.proptech.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findTop10ByReceiveUserAndReadDateIsNull(User receiveUser);
}
