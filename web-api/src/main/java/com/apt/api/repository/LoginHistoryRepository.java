package com.apt.api.repository;


import com.apt.api.domain.LoginHistory;
import com.apt.api.domain.User;
import com.apt.api.domain.enums.IpChecked;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {


   LoginHistory findTopByUserOrderByIdDesc(User user);

   //특정 유저의 특정 IP의 가장 최신 상태를 찾기 위한 메소드
   LoginHistory findTopByUserAndIpOrderByIdDesc(User user, String ip);

   //특정 상태의 IP를 찾기 위한 메소드
   @Query(value = "SELECT h FROM LoginHistory h WHERE h.user = :user AND h.ipChecked = :checked GROUP BY ip")
   List<LoginHistory> findByUserAndIpChecked(IpChecked checked, User user);

   //특정 상태가 아닌 IP를 찾기위한 메소드
   @Query(value = "SELECT h FROM LoginHistory h WHERE h.user = :user AND h.ipChecked != :checked GROUP BY ip")
   List<LoginHistory> findByUserAndExceptIpChecked(IpChecked checked, User user);

   List<LoginHistory> findByUserOrderByIdDesc(User user);

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
