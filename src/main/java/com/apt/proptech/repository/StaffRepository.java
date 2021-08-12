package com.apt.proptech.repository;


import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.Staff;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    @EntityGraph(attributePaths = "user")
    List<Staff> findByAssociate(Associate associate);
}
