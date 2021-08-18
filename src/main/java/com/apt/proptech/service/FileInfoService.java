package com.apt.proptech.service;

import com.apt.proptech.domain.FileInfo;
import com.apt.proptech.repository.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileInfoService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    private FileInfo addItem( FileInfo fileInfo){

            FileInfo info = FileInfo.builder()
                    .filename(fileInfo.getFilename())
                    .origFilename(fileInfo.getOrigFilename())
                    .filePath(fileInfo.getFilePath())
                    .build();

            fileInfoRepository.save(info);

            return  info;

    }

}
