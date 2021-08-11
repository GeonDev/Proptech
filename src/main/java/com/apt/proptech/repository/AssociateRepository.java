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

    @Query(value = "SELECT " +
                    "* " +
                    "FROM associate " +
                    "WHERE city =:address " +
                    "OR state =:address " +
                    "OR address =:address " +
                    "ORDER BY id DESC" ,nativeQuery = true)
    Page<Associate> findByAssociateAddress(@Param("address") String address, Pageable pageable);

    @Query(value = "SELECT " +
                    "* " +
                    "FROM associate " +
                    "WHERE associate_round =:round " +
                    "ORDER BY id DESC" ,nativeQuery = true)
    Page<Associate> findByRound(@Param("round") String round, Pageable pageable);



    List<Associate> findByAssociateRoundNot(AssociateRound round);

    @Query(value = "SELECT ROUND((SUM(\n" +
            "\t\tCASE \n" +
            "\t\tWHEN associate_round = 'CREATE' Then 1\n" +
            "\t\tWHEN associate_round = 'SIGNED' Then 2\n" +
            "\t\tWHEN associate_round = 'SELECTION' Then 3\n" +
            "\t\tWHEN associate_round = 'COMPLETION' Then 4\n" +
            "\t\tWHEN associate_round = 'SALE' Then 5\n" +
            "\t\tWHEN associate_round = 'FINISH' Then 5\n" +
            "\t\tEND) /\n" +
            "\t\t(COUNT(*)*6))*100, :round ) persent\t\t\n" +
            "\t\tFROM associate ", nativeQuery = true)
    String findAssociateTaskPercent(@Param("round") int round);


    Page<Associate> findByNameContaining(String name, Pageable pageable);
}
