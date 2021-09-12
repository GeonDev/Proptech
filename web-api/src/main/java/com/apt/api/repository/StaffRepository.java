package com.apt.api.repository;


import com.apt.api.domain.Associate;
import com.apt.api.domain.Staff;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    @EntityGraph(attributePaths = "user")
    List<Staff> findByAssociate(Associate associate);
}
