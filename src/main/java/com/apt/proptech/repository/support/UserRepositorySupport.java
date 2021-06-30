package com.apt.proptech.repository.support;

import com.apt.proptech.domain.User;

import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.util.CommonUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


//중요!  QueryDsl에서 QUser에서 추출된 user 클래스를 생성해야 한다.
import static com.apt.proptech.domain.QUser.user;


@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    @Autowired
    private final JPAQueryFactory queryFactory;

    public UserRepositorySupport(JPAQueryFactory queryFactory){
        super(User.class);
        this.queryFactory = queryFactory;
    }


    public User findOneByName(String name){
        return queryFactory.selectFrom(user).where(user.name.eq(name))
                .fetchOne();
    }


    public List<User> findUserNameAndDate(String value, String startDate, String endDate ){
        return  queryFactory.selectFrom(user).where(containsName(value), betweenDate(startDate,endDate) ,lessThenDate(startDate, endDate)).fetch();
    }

    private BooleanExpression containsName(String value){
        if(CommonUtil.null2str(value).equals("") ) {
            return  null;
        }
        return user.name.contains(value);
    }




    private BooleanExpression eqRole (String str){
        if(CommonUtil.null2str(str).equals("") ) {
            return  null;
        }

        if("ROLE_ADMIN".contains(str) ){
            UserRole role = UserRole.ROLE_ADMIN;
            return user.userRole.eq(role);
        }else if("ROLE_USER".contains(str)) {
            UserRole role = UserRole.ROLE_USER;
            return user.userRole.eq(role);
        }else if("ROLE_MANAGER".contains(str) ){
            UserRole role = UserRole.ROLE_MANAGER;
            return user.userRole.eq(role);
        }else if("ROLE_STAFF".contains(str) ){
            UserRole role = UserRole.ROLE_STAFF;
            return user.userRole.eq(role);
        }else if("ROLE_PARTNER".contains(str) ) {
            UserRole role = UserRole.ROLE_PARTNER;
            return user.userRole.eq(role);
        }else{
            //모든 경우에 해당되지 않을때
            return  null;
        }
    }




    private BooleanExpression betweenDate(String startDate, String endDate){

        if(!CommonUtil.null2str(startDate).equals("") &&  !CommonUtil.null2str(endDate).equals("") ){
            LocalDateTime start = CommonUtil.toStringLocalDateTime(startDate);
            LocalDateTime end = CommonUtil.toStringLocalDateTime(endDate);

            return  user.regDate.between(start,end);
        }else{
            return  null;
        }
    }

    private  BooleanExpression lessThenDate(String startDate, String endDate){

        if(CommonUtil.null2str(startDate).equals("") && !CommonUtil.null2str(startDate).equals("")){
            LocalDateTime end = CommonUtil.toStringLocalDateTime(endDate);
            return  user.regDate.before(end);

        }else{
            return  null;
        }
    }
}
