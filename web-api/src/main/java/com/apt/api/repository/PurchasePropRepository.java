package com.apt.api.repository;


import com.apt.api.domain.Associate;
import com.apt.api.domain.PurchaseProp;
import com.apt.api.domain.enums.PropType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasePropRepository extends JpaRepository<PurchaseProp, Long> {

    @EntityGraph(attributePaths = "priceList")
    List<PurchaseProp> findByAssociateAndPropType(Associate associate, PropType type);
}
