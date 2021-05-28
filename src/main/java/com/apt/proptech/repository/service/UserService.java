package com.apt.proptech.repository.service;

import com.apt.proptech.domain.User;
import com.apt.proptech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User>{

    @Override
    public User addItem(User object) {

        User user = User.builder()
                .id(object.getId())
                .password(object.getPassword())
                .name(object.getName())
                .email(object.getEmail())
                .build();

        baseRepository.save(user);

        return user;
    }

    @Override
    public User getItem(Long id ) {
        return baseRepository.findById(id).orElse(null);
    }

    @Override
    public User updateItem(User object) {


        return null;
    }

    @Override
    public User deleteItem(Long id) {

        User user = baseRepository.findById(id).orElse(null);

        if(user!= null){
            baseRepository.delete(user);
        }

        return user;
    }
}
