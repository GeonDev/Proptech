package com.apt.proptech.core.repository.support;

import com.apt.proptech.core.domain.LoginHistory;
import com.apt.proptech.core.domain.User;
import com.apt.proptech.core.domain.enums.IpChecked;
import com.apt.proptech.core.util.CommonUtil;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.apt.proptech.core.domain.QLoginHistory.loginHistory;

@Repository
public class LoginHistoryRepositorySupport extends QuerydslRepositorySupport {

    @Autowired
    private final JPAQueryFactory queryFactory;

    public LoginHistoryRepositorySupport(JPAQueryFactory queryFactory) {
        super(LoginHistory.class);
        this.queryFactory = queryFactory;
    }


    //특정 유저의 기록 중 특정상태인 N개만 조회
    public List<LoginHistory> findLoginHistoryLimitAndOrder (User user, IpChecked ipChecked, String type, int count, String order ){

        return queryFactory.selectFrom(loginHistory)
                .where(loginHistory.user().eq(user), eqIpCheckedType(ipChecked,type))
                .groupBy(loginHistory.id )
                .orderBy(setOrder(order))
                .limit(count)
                .fetch();
    }


    //타입에 따라 특정 IpChecked만 출력하거나 해당 IP만 제외하거나
    private BooleanExpression eqIpCheckedType(IpChecked ipChecked, String type){

        if(CommonUtil.null2str(type).equals("") || ipChecked == null){
            return null;
        }

        if(type.equals("only") ){
            return loginHistory.ipChecked.eq(ipChecked);
        }else if(type.equals("except") ){
            return loginHistory.ipChecked.ne(ipChecked);
        }else{
            return null;
        }

    }



    private OrderSpecifier<Long> setOrder(String value){

        if(CommonUtil.null2str(value).equals("") ) {
            return loginHistory.id.desc();
        }

        value = value.toUpperCase();

        if(value.equals("ASC") ){
            return loginHistory.id.desc();
        }else{
            return loginHistory.id.asc();
        }
    }


}
