package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.Message;
import com.apt.proptech.core.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @EntityGraph(attributePaths = "receiveUser")
    List<Message> findTop10ByReceiveUserAndReadDateIsNullOrderByIdDesc(User receiveUser);

    @Query(value = "SELECT *\n" +
            "FROM   message\n" +
                    "JOIN user\n" +
                     "ON user.id = message.receive_user_id\n" +
            "WHERE  message.read_date IS NULL\n" +
                    "AND user.username = :userName\n" +
            "ORDER  BY message.id DESC\n" +
            "LIMIT  10 ", nativeQuery = true)
    List<Message> findByNotCheckedMessage(String userName);

}
