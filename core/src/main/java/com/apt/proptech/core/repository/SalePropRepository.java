package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.Associate;
import com.apt.proptech.core.domain.SaleProp;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalePropRepository extends JpaRepository<SaleProp, Long> {

    @EntityGraph(attributePaths = "claimPropList")
    List<SaleProp> findByAssociate(Associate associate);
}
