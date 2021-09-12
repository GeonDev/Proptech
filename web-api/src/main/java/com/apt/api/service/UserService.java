package com.apt.api.service;


import com.apt.api.config.TableColumnConfig;
import com.apt.api.domain.LoginHistory;
import com.apt.api.domain.User;
import com.apt.api.domain.dto.ColumnTitle;
import com.apt.api.domain.dto.Pagination;
import com.apt.api.domain.dto.UserDto;
import com.apt.api.domain.enums.UserRole;
import com.apt.api.domain.enums.UserState;
import com.apt.api.repository.AccountRepository;
import com.apt.api.repository.LoginHistoryRepository;
import com.apt.api.repository.UserRepository;
import com.apt.api.repository.support.UserRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService<User>{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositorySupport userRepositorySupport;

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Autowired
    private AccountRepository accountRepository;



    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TableColumnConfig tableConfig;



    @Override
    public User addItem(User object) {

        User user = User.builder()
                .username(object.getUsername())
                .password(bCryptPasswordEncoder.encode(object.getPassword()))
                .name(object.getName())
                .email(object.getEmail())
                .userRole(object.getUserRole())
                .userState(object.getUserState())
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
        user.setPhoneNumber(object.getPhoneNumber());

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

    public User getItem(String userName ) {
        return userRepository.findByUsername(userName);
    }


    public Pagination getItemList(Pageable pageable, String type, String value, String startDate, String endDate) {

        PageImpl<User> userPage = userRepositorySupport.findUserTypeAndDatePage(type, value, startDate, endDate, pageable);

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
                .prePageNum(setPrePageNum(userPage.getNumber(), userPage.isFirst()) )
                .nextPageNum(setNextPageNum(userPage.getNumber(), userPage.isLast()))
                .searchType(setSearchType())
                .columnTitles(setColumns())
                .build();

        return items;
    }


    public List<UserDto> getExcelDate(String type, String value, String startDate, String endDate){
        List<User> temp = userRepositorySupport.findUserTypeAndDate(type,value,startDate,endDate);

        List<UserDto> items = new ArrayList<>();
        temp.forEach(o->{
            LoginHistory history = loginHistoryRepository.findTopByUserOrderByIdDesc(o);
            items.add(new UserDto(o,history));
        });

        return  items;
    }

    public int getUserRoleAndExceptStateCount(UserRole role, UserState state){
        return  userRepository.findByUserRoleAndUserStateNot(role, state).size();
    }

    private List<UserDto> convertDomain( List<User> data){
        List<UserDto> result = new ArrayList<UserDto>();

        for(User info : data ){

            LoginHistory history = loginHistoryRepository.findTopByUserOrderByIdDesc(info);
            UserDto temp = new UserDto(info, history);
            result.add(temp);
        }

        return  result;
    }


    private List<String> setSearchType(){
        List<String> temp = new ArrayList<>();

        List<String> column = tableConfig.getUserSearch();

        for(int i =0; i< column.size(); i++ ){
            temp.add(column.get(i));
        }

        return temp;
    }


    private List<ColumnTitle> setColumns(){
        List<ColumnTitle> temp = new ArrayList<>();

        List<String> column = tableConfig.getUserColumn();

        for(int i =0; i< column.size(); i++ ){
            temp.add(new ColumnTitle(column.get(i), "c"+i ) );
        }

        return temp;
    }









}