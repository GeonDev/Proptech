package com.apt.proptech.repository.support;

import com.apt.proptech.domain.*;
import com.apt.proptech.domain.enums.AssociateRound;
import com.apt.proptech.util.CommonUtil;
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

import static com.apt.proptech.domain.QAssociate.associate;

@Repository
public class AssociateRepositorySupport extends QuerydslRepositorySupport {

    @Autowired
    private final JPAQueryFactory queryFactory;

    public AssociateRepositorySupport(JPAQueryFactory queryFactory) {
        super(Associate.class);
        this.queryFactory = queryFactory;
    }


    //모든 데이터 조회 (엑셀 데이터 추출 용)
    public List<Associate> findAssociateTypeAndDate(String type, String value, String startDate, String endDate){
        return queryFactory.selectFrom(associate).where(eqTypeAndValue(type, value), betweenDate(startDate, endDate))
                .fetch();
    }



    public PageImpl<Associate> findUserTypeAndDatePage(String type, String value, String startDate, String endDate , Pageable pageable){

        JPAQuery<Associate> query = queryFactory.selectFrom(associate)
                .where( eqTypeAndValue(type, value), betweenDate(startDate, endDate));

        Long totalCount  = query.fetchCount();

        List<Associate> result = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(result, pageable, totalCount);
    }



    private BooleanExpression eqTypeAndValue(String type, String value){

        if(type.equals("단계") ){
            return eqRound(value);
        }else if(type.equals("주소")) {
            return containAddress(value);
        }else if(type.equals("이름")){
            return eqName(value);
        }else{
            return null;
        }
    }


    private BooleanExpression eqName(String value){
        if(CommonUtil.null2str(value).equals("") ) {
            return  null;
        }
        return associate.name.eq(value);
    }

    private BooleanExpression containAddress(String value){
        if(CommonUtil.null2str(value).equals("") ) {
            return  null;
        }
        return associate.address.contains(value);
    }



    private BooleanExpression eqRound(String value){
        if(CommonUtil.null2str(value).equals("") ) {
            return  null;
        }
        value = value.toUpperCase();

        if("CREATE".equals(value) ){
            return associate.associateRound.eq(AssociateRound.CREATE);
        }else if("SIGNED".equals(value)){
            return associate.associateRound.eq(AssociateRound.SIGNED);
        }else if("SELECTION".equals(value) ){
            return  associate.associateRound.eq(AssociateRound.SELECTION);
        }else if("COMPLETION".equals(value) ){
            return  associate.associateRound.eq(AssociateRound.COMPLETION);
        }else if("SALE".equals(value) ){
            return  associate.associateRound.eq(AssociateRound.SALE);
        }else if ("INACTIVE".equals(value)){
            return  associate.associateRound.eq(AssociateRound.FINISH);
        }else{
            return null;
        }
    }

    private BooleanExpression betweenDate(String startDate, String endDate){

        if(!CommonUtil.null2str(startDate).equals("") &&  !CommonUtil.null2str(endDate).equals("") ){
            LocalDateTime start = CommonUtil.toStringLocalDateTime(startDate);
            LocalDateTime end = CommonUtil.toStringLocalDateTime(endDate);
            return  associate.regDate.between(start,end);
        }else if(CommonUtil.null2str(startDate).equals("") && !CommonUtil.null2str(endDate).equals("")){
            LocalDateTime end = CommonUtil.toStringLocalDateTime(endDate);
            return  associate.regDate.before(end);
        } else if(!CommonUtil.null2str(startDate).equals("") && CommonUtil.null2str(endDate).equals(""))  {
            LocalDateTime start = CommonUtil.toStringLocalDateTime(startDate);
            return  associate.regDate.after(start);
        }else{
            return  null;
        }
    }

}
