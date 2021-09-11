package com.apt.proptech.core.repository;

import com.apt.proptech.core.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
}
