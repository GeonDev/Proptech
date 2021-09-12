package com.apt.api.repository;


import com.apt.api.domain.Alert;
import com.apt.api.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    @EntityGraph(attributePaths = "receiveUser")
    List<Alert> findTop10ByReceiveUserAndReadDateIsNull(User receiveUser);

    @Query(value = "SELECT * " +
                    "FROM alert " +
                            "JOIN user " +
                              "ON user.id = alert.receive_user_id " +
                    "WHERE alert.read_date IS NULL " +
                        "AND user.username =:userName " +
                    "ORDER BY alert.id DESC " +
                    "LIMIT 10", nativeQuery = true)
    List<Alert> findByNotCheckedMessage(String userName);



}
