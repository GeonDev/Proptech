package com.apt.proptech.service;

import com.apt.proptech.config.TableColumnConfig;
import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.dto.ColumnTitle;
import com.apt.proptech.domain.dto.Pagination;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.dto.UserDto;
import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import com.apt.proptech.repository.LoginHistoryRepository;
import com.apt.proptech.repository.UserRepository;
import com.apt.proptech.repository.support.UserRepositorySupport;
import com.apt.proptech.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Override
    public Pagination getItemList(Pageable pageable, String type, String value) {

        Page<User> userPage = null;

        if(type.equals("Role")){
            userPage = userRepository.findAllByUserRoles(value, pageable);
        }else if(type.equals("State")){
            userPage = userRepository.findAllByUserState(value, pageable);
        }else{
            userPage = baseRepository.findAll(pageable);
        }

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
        temp.forEach(o->{ items.add(new UserDto(o)); });

        return  items;
    }

    public List<UserDto> getUserRoleAndExceptState(UserRole role, UserState state){
        List<User> temp =userRepository.findByUserRoleAndUserStateNot(role, state);

        List<UserDto> items = new ArrayList<>();
        temp.forEach(o->{ items.add(new UserDto(o)); });

        return  items;
    }

    private List<UserDto> convertDomain( List<User> data){
        List<UserDto> result = new ArrayList<UserDto>();

        for(User info : data ){
            UserDto temp = new UserDto(info);
            LoginHistory history = loginHistoryRepository.findTopByUserOrderByIdDesc(info);
            if(history!=null ){
                temp.setLastLoginDate(CommonUtil.toDateStr(history.getLoginDate()));
            }else{
                temp.setLastLoginDate("");
            }
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
            temp .add(new ColumnTitle(column.get(i), "c"+i ) );
        }

        return temp;
    }




}