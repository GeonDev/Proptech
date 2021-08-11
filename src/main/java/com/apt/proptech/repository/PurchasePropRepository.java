package com.apt.proptech.repository;

import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.PurchaseProp;
import com.apt.proptech.domain.enums.PropType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasePropRepository extends JpaRepository<PurchaseProp, Long> {

    List<PurchaseProp> findByAssociateAndPropType(Associate associate, PropType type);
}
