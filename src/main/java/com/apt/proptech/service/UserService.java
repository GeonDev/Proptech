package com.apt.proptech.service;

import com.apt.proptech.config.TableColumnConfig;
import com.apt.proptech.domain.Account;
import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.LoginIp;
import com.apt.proptech.domain.dto.*;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import com.apt.proptech.repository.AccountRepository;
import com.apt.proptech.repository.LoginHistoryRepository;
import com.apt.proptech.repository.LoginIpRepository;
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
import java.util.Optional;

@Service
public class UserService extends BaseService<User>{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositorySupport userRepositorySupport;

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Autowired
    private LoginIpRepository loginIpRepository;

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

    public List<LoginIpDto> getLoginIpInfo(User user){
        List<LoginIpDto> list = new ArrayList<>();

        List<LoginIp> temp = loginIpRepository.findTop5ByUserOrderByIdDesc(user);

        if(temp !=null && !temp.isEmpty() ){
            for( LoginIp ip : temp ){
                list.add( new LoginIpDto(ip));
            }
        }

        return list;
    }

    public List<LoginHistoryDto> getLoginHistoryInfo(User user ){
        List<LoginHistoryDto> list = new ArrayList<>();

        List<LoginHistory> temp = loginHistoryRepository.findTop5ByUserOrderByIdDesc(user);

        if(temp !=null && !temp.isEmpty() ){
            for( LoginHistory history : temp ){
                list.add( new LoginHistoryDto(history));
            }
        }

        return  list;
    }

    public List<AccountDto> getAccountInfo(User user){
        List<AccountDto> list = new ArrayList<>();

        List<Account> temp = accountRepository.findByUserOrderByIdDesc(user);

        if(temp != null && !temp.isEmpty() ){
            for( Account account : temp){
                list.add(new AccountDto( account) );
            }
        }

        return list;
    }



}