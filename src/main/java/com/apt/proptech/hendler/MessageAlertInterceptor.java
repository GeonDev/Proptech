package com.apt.proptech.hendler;

import com.apt.proptech.domain.Alert;
import com.apt.proptech.domain.Message;
import com.apt.proptech.domain.User;
import com.apt.proptech.service.AlertService;
import com.apt.proptech.service.MessageService;
import com.apt.proptech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Component
public class MessageAlertInterceptor implements HandlerInterceptor {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AlertService alertService;

    @Autowired
    private UserService userService;
    
    //view 생성 후 랜더링 되기전 실행
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView ) throws Exception {

        //세션이 있을때 만 정보를 가지고 온다.
        HttpSession session = request.getSession(false);

        if( session!= null){
            //읽지 않은 알람, 메세지가 있는 지 확인한다.
            List<Alert> alertList = alertService.getAlertListNotRead((String)session.getAttribute("userName"));
            List<Message> messageList =  messageService.getMessageListNotRead((String)session.getAttribute("userName"));

            if(alertList.isEmpty()){
                session.setAttribute("totalAlerts","");
            }else{
                session.setAttribute("totalAlerts",alertList.size());
                session.setAttribute("alertList",alertList);
            }

            if(messageList.isEmpty()){
                session.setAttribute("totalMessages","");
            }else{
                session.setAttribute("totalMessages",messageList.size());
                session.setAttribute("messageList",messageList);
            }
        }
    }

}
