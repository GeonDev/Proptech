package com.apt.proptech.scheduler;

import com.apt.proptech.service.LoginHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class CustomSchduler {

    @Autowired
    LoginHistoryService loginHistoryService;

    public static final Logger logger = LoggerFactory.getLogger(CustomSchduler.class);

    //매월 1일 0시 0분 0초에 실행
    @Scheduled(cron="0 0 0 1 * *" )
    public void setPreferUserCategory() {

        try {
            Integer row =  loginHistoryService.DeleteLoginHistoryBeforeMonths(1);
            logger.info("[SUCCESS] : DELETE LOGIN HISTORY BEFORE 1 MONTH ROWS : {}" ,row);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }


    }


}
