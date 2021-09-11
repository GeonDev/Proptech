package com.apt.proptech.core.service;

import com.apt.proptech.core.domain.Message;
import com.apt.proptech.core.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessageListNotRead(String userName ){
        return  messageRepository.findByNotCheckedMessage(userName);
    }

    @Transactional
    public Message sendMessage(Message message ){
        return  messageRepository.save(message);
    }


    public  Message readMessage(Message message){

        Message temp = messageRepository.findById(message.getId()).orElse(null);

        if(temp !=null ){
            temp.setReadDate(LocalDateTime.now());
            return messageRepository.save(temp);
        }

        return  null;
    }

}
