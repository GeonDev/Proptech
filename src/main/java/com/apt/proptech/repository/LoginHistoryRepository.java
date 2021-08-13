package com.apt.proptech.repository;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

   LoginHistory findTopByUserOrderByIdDesc(User user);


   List<LoginHistory> findTop10ByUserOrderByIdDesc(User user);

   @Transactional
   @Modifying(flushAutomatically = true, clearAutomatically = true)
   @Query(value = "DELETE FROM login_history " +
                   "WHERE user_id IN :userIds " +
                   "AND login_date < :date " ,nativeQuery = true )
   Integer deleteAllUserLoginHistory(@Param("userIds") List<String> userIds, @Param("date") LocalDateTime date) ;

   @Transactional
   @Modifying(flushAutomatically = true, clearAutomatically = true)
   @Query(value = "DELETE FROM login_history " +
                  "WHERE login_date < :date " ,nativeQuery = true )
   Integer deleteAllUserLoginHistory( @Param("date") LocalDateTime date) ;



}
