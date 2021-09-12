package com.apt.api.repository.support;


import com.apt.api.domain.User;
import com.apt.api.domain.enums.UserRole;
import com.apt.api.domain.enums.UserState;
import com.apt.api.util.CommonUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.apt.api.domain.QUser.user;



@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    @Autowired
    private final JPAQueryFactory queryFactory;

    public UserRepositorySupport(JPAQueryFactory queryFactory){
        super(User.class);
        this.queryFactory = queryFactory;
    }

    //모든 데이터 조회 (엑셀 데이터 추출 용)
    public List<User> findUserTypeAndDate(String type, String value, String startDate, String endDate ){
        return  queryFactory.selectFrom(user)
                .where(eqTypeAndValue(type, value), betweenDate(startDate,endDate) ).fetch();
    }

    // PageImpl은 Spring Data에서 이미 선언되어 있는 도메인
    public PageImpl<User> findUserTypeAndDatePage(String type, String value, String startDate, String endDate , Pageable pageable){

        JPAQuery<User> query = queryFactory.selectFrom(user)
                .where(eqTypeAndValue(type, value), betweenDate(startDate,endDate));

        Long totalCount = query.fetchCount();

        //pageable 수행
        List<User> result = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(result, pageable, totalCount);
    }


    private BooleanExpression eqTypeAndValue(String type, String value){

        if(type.equals("권한") ){
            return eqRole(value);
        }else if(type.equals("상태")) {
            return eqState(value);
        }else if(type.equals("사용자명")){
            return eqName(value);
        }else{
            return null;
        }
    }


    private BooleanExpression eqName(String value){
        if(CommonUtil.null2str(value).equals("") ) {
            return  null;
        }
        return user.name.eq(value);
    }

    private BooleanExpression eqState(String value){
        if(CommonUtil.null2str(value).equals("") ) {
            return  null;
        }

        value = value.toUpperCase();

        if("UN_AUTH".equals(value) ){
            return user.userState.eq(UserState.UN_AUTH);
        }else if("AUTH".equals(value)){
            return user.userState.eq(UserState.AUTH);
        }else if("RETIRED".equals(value)){
            return user.userState.eq(UserState.RETIRED);
        } else{
            return  null;
        }
    }


    private BooleanExpression eqRole (String value){
        if(CommonUtil.null2str(value).equals("") ) {
            return  null;
        }

        value = value.toUpperCase();

        if("ROLE_ADMIN".equals(value) ){
            return user.userRole.eq(UserRole.ROLE_ADMIN);
        }else if("ROLE_USER".equals(value)) {
            return user.userRole.eq(UserRole.ROLE_USER);
        }else if("ROLE_MANAGER".equals(value) ){
            return user.userRole.eq(UserRole.ROLE_MANAGER);
        }else if("ROLE_STAFF".equals(value) ){
            return user.userRole.eq(UserRole.ROLE_STAFF);
        }else if("ROLE_PARTNER".equals(value) ) {
            return user.userRole.eq(UserRole.ROLE_PARTNER);
        }else{
            return  null;
        }
    }




    private BooleanExpression betweenDate(String startDate, String endDate){

        if(!CommonUtil.null2str(startDate).equals("") &&  !CommonUtil.null2str(endDate).equals("") ){
            LocalDateTime start = CommonUtil.toStringLocalDateTime(startDate);
            LocalDateTime end = CommonUtil.toStringLocalDateTime(endDate);
            return  user.regDate.between(start,end);
        }else if(CommonUtil.null2str(startDate).equals("") && !CommonUtil.null2str(endDate).equals("")){
            LocalDateTime end = CommonUtil.toStringLocalDateTime(endDate);
            return  user.regDate.before(end);
        } else if(!CommonUtil.null2str(startDate).equals("") && CommonUtil.null2str(endDate).equals(""))  {
            LocalDateTime start = CommonUtil.toStringLocalDateTime(startDate);
            return  user.regDate.after(start);
        }else{
            return  null;
        }
    }
}