package com.apt.proptech.service;

import com.apt.proptech.domain.dto.Pagination;
import com.apt.proptech.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User>{

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public User addItem(User object) {

        System.out.println("USER : " + object.getName() + "ADD!!" );


        User user = User.builder()
                .username(object.getUsername())
                .password(encoder.encode(object.getPassword()))
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

        User user = baseRepository.findById(object.getId()).orElse(null);

        user.setName(object.getName());
        user.setEmail(object.getEmail());
        user.setPassword(object.getPassword());
        user.setProfileImg(object.getProfileImg());

        baseRepository.save(user);

        return user;
    }

    @Override
    public User deleteItem(Long id) {

        User user = baseRepository.findById(id).orElse(null);

        if(user!= null){
            baseRepository.delete(user);
        }

        return user;
    }

    @Override
    public Pagination getItemList(Pageable pageable) {

        Page<User> userPage = baseRepository.findAll(pageable);

        //화면에 표시하기 위한 Pagination 세팅
        Pagination<User> items = Pagination.<User>builder()
                .totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .currentPage(userPage.getNumber())
                .currentElements(userPage.getNumberOfElements())
                .contents(userPage.getContent())
                .build();

        return items;
    }

}
