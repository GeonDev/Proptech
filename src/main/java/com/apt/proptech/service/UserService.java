package com.apt.proptech.service;

import com.apt.proptech.domain.dto.Pagination;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService<User>{

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public User addItem(User object) {

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
    public Pagination getItemList(Pageable pageable, String type, String value) {

        Page<User> userPage = baseRepository.findAll(pageable);

        //화면에 표시하기 위한 Pagination 세팅
        Pagination<UserDto> items = Pagination.<UserDto>builder()
                .isFirstPage(userPage.isFirst())
                .isLastPage(userPage.isLast())
                .totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .currentPage(userPage.getNumber()+1)
                .currentElements(userPage.getNumberOfElements()+1)
                .contents(convertDomain(userPage.getContent()))
                .pageNumbers(setPageNumber(userPage.getNumber(), userPage.getSize(), userPage.getTotalPages()))
                .build();

        return items;
    }

    private List<UserDto> convertDomain( List<User> data){
        List<UserDto> result = new ArrayList<UserDto>();

        for(User info : data ){
            UserDto temp = new UserDto(info);
            result.add(temp);
        }

        return  result;
    }

    private List<Integer> setPageNumber(int currentPage, int pageSize, int totalPages ){

        List<Integer> pageNum = new ArrayList<>();

        int size = pageSize/2;

        int start = currentPage- size;
        if(start < 0){
            start = 0;
        }

        int end = currentPage + size;
        if( end > totalPages){
            end = totalPages;
        }

        for(int i = start; i< currentPage; i++){
            pageNum.add(i+1);
        }
        for(int i = currentPage; i< end; i++){
            pageNum.add(i+1);
        }

        return  pageNum;
    }

}
