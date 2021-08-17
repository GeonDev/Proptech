package com.apt.proptech.config;

import com.apt.proptech.hendler.MessageAlertInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Autowired
    private MessageAlertInterceptor messageNotiInterceptor;


    @Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //인터럽트를 수행할 URL
        List<String> checkUrlList = new ArrayList<>();
        checkUrlList.add("/dashboard/**");
        checkUrlList.add("/table/**");
        checkUrlList.add("/chart/**");


        //인터럽트를 수행 하지 않는 URL
        List<String> excludeUrlList = new ArrayList<>();
        excludeUrlList.add("/");
        excludeUrlList.add("/login**");
        excludeUrlList.add("/register**");
        excludeUrlList.add("/userinfo**");
        excludeUrlList.add("/forgot**");
        excludeUrlList.add("/logout**");
        excludeUrlList.add("/api**");
        excludeUrlList.add("/css**");
        excludeUrlList.add("/img**");
        excludeUrlList.add("/js**");
        excludeUrlList.add("/error/**");

        //addPathPatterns 해당 패턴에 해당하는 URL을 인터럽트한다.
        //excludePathPatterns 해당 패턴에 해당하는 URL은 인터럽트하지 않는다.
        registry.addInterceptor(messageNotiInterceptor).addPathPatterns(checkUrlList).excludePathPatterns(excludeUrlList);


    }


}
