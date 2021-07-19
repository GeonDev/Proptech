package com.apt.proptech.repository;

import com.apt.proptech.domain.Message;
import com.apt.proptech.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @EntityGraph(attributePaths = "receiveUser")
    List<Message> findTop10ByReceiveUserAndReadDateIsNullOrderByIdDesc(User receiveUser);

    @EntityGraph(attributePaths = "sendUser")
    @Query(value = "SELECT * FROM message JOIN user ON user.id = message.receive_user_id WHERE message.read_date IS NULL AND user.username =:userName ORDER BY message.id DESC LIMIT 10", nativeQuery = true)
    List<Message> findByNotCheckedMessage(String userName);

}
