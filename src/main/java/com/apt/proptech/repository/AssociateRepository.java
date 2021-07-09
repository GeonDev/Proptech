package com.apt.proptech.repository;


import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.AssociateRound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssociateRepository extends JpaRepository<Associate, Long> {

    @Query(value = "SELECT * FROM associate WHERE city =:address or state =:address or address =:address ORDER BY id DESC" ,nativeQuery = true)
    Page<Associate> findByAssociateAddress(@Param("address") String address, Pageable pageable);

    @Query(value = "SELECT * FROM associate WHERE associate_round =:round ORDER BY id DESC" ,nativeQuery = true)
    Page<Associate> findByRound(@Param("round") String round, Pageable pageable);

    Page<Associate> findByNameContaining(String name, Pageable pageable);

    List<Associate> findByAssociateRoundNot(AssociateRound round);

}
