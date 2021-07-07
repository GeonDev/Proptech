package com.apt.proptech.hendler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class MessageNotiInterceptor implements HandlerInterceptor {

    
    
    //view 생성 후 랜더링 되기전 실행
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        //메세지 전달
        modelAndView.addObject("totalAlerts","");
        modelAndView.addObject("totalMessages","");

    }

    //view가 렌더링 완료 된 후 실행
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
