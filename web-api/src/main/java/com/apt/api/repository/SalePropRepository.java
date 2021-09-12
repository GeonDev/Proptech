package com.apt.api.repository;


import com.apt.api.domain.Associate;
import com.apt.api.domain.SaleProp;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalePropRepository extends JpaRepository<SaleProp, Long> {

    @EntityGraph(attributePaths = "claimPropList")
    List<SaleProp> findByAssociate(Associate associate);
}
