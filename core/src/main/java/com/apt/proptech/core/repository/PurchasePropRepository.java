package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.Associate;
import com.apt.proptech.core.domain.PurchaseProp;
import com.apt.proptech.core.domain.enums.PropType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasePropRepository extends JpaRepository<PurchaseProp, Long> {

    @EntityGraph(attributePaths = "priceList")
    List<PurchaseProp> findByAssociateAndPropType(Associate associate, PropType type);
}
