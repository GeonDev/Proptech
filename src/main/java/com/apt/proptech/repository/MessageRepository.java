package com.apt.proptech.repository;

import com.apt.proptech.domain.Message;
import com.apt.proptech.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findTop10ByReceiveUserAndReadDateIsNullOrderByIdDesc(User receiveUser);

}
