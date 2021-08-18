package com.apt.proptech.hendler;

import com.apt.proptech.controller.MainController;
import com.apt.proptech.domain.FileInfo;
import com.apt.proptech.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUploadHandler {

    static final Logger LOGGER = LoggerFactory.getLogger(FileUploadHandler.class);

    @Value("@{config.file}")
    private String path;

    public FileInfo parseFileInfo( MultipartFile multipartFile){
            if(!multipartFile.isEmpty() || multipartFile != null ){

                File file = new File(path);

                //해당 디렉토리가 있는지 확인 - 없으면 생성 시도
                if(!file.exists() ){
                    boolean mkdir = file.mkdirs();

                    if(!mkdir){
                        //생성 실패시 업로드 종료
                        LOGGER.info("[ERROR] FAIL MAKE DIRECTORY => {}", path);
                        return null;
                    }
                }

                //이미지 업로드 시도도
               return uploadImage(multipartFile);
            }

        return null;
    }


    public List<FileInfo> parseFileInfo(List<MultipartFile> multipartFiles){
        if(!multipartFiles.isEmpty() || multipartFiles != null ){

            File file = new File(path);

            //해당 디렉토리가 있는지 확인 - 없으면 생성 시도
            if(!file.exists() ){
                boolean mkdir = file.mkdirs();

                if(!mkdir){
                    //생성 실패시 업로드 종료
                    LOGGER.info("[ERROR] FAIL MAKE DIRECTORY => {}", path);
                    return null;
                }
            }

            List<FileInfo> list = new ArrayList<>();
            for(MultipartFile multipartFile : multipartFiles ){
                FileInfo temp = uploadImage(multipartFile);
                if(temp != null ){
                    list.add(temp);
                }
            }
            return list;
        }else{
            return null;
        }
    }



    private FileInfo uploadImage( MultipartFile multipartFile ){

        //파일 확장자를 보고 이미지 인지 확인 - 이미지가 아니면 처리 안함
        if(multipartFile.getContentType().contains("image/jpeg") || multipartFile.getContentType().contains("image/png") ){

            //시간을 이용하여 이름이 중복되지 않게 처리
            String inDate = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());

            String fileName = multipartFile.getName() +"_"+ inDate;

            FileInfo info = FileInfo.builder()
                    .filePath(path +File.separator +  fileName)
                    .origFilename(multipartFile.getOriginalFilename())
                    .filename(fileName)
                    .build();

            //이미지 파일 업로드 시도
            try {
                File f = new File(info.getFilePath());
                multipartFile.transferTo(f);
                return info;
            } catch (IOException e) {
                LOGGER.info("[ERROR] FAIL IMAGE FILE UPLOAD  => {}", info.getFilePath());
                e.printStackTrace();
            }
        }

        return null;
    }

}
