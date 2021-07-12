package com.apt.proptech.service;

import com.apt.proptech.domain.Message;
import com.apt.proptech.domain.User;
import com.apt.proptech.repository.MessageRepository;
import com.apt.proptech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Message> getMessageListNotRead(String userName ){

        User receiveUser = userRepository.findByUsername(userName);
        if(receiveUser != null){
            return  messageRepository.findTop10ByReceiveUserAndReadDateIsNullOrderByIdDesc(receiveUser);
        }else{
            return null;
        }
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
