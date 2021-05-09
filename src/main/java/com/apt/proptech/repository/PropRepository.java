package com.apt.proptech.repository;

import com.apt.proptech.domain.Prop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropRepository extends JpaRepository<Prop, Long> {
}
