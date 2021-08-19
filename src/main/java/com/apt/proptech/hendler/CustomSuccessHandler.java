package com.apt.proptech.hendler;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.IpChecked;
import com.apt.proptech.repository.LoginHistoryRepository;
import com.apt.proptech.repository.UserRepository;
import com.apt.proptech.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        //로그인 성공시 로그인 실패 카운트 초기화 - 로그인에 성공하였음으로 반드시 유저정보가 있다.
        User user = userRepository.findByUsername(authentication.getName());

        user.setFailLoginCount(0);

        //세션은 단순 화면 표시용 기능 -> 실제 연산은 시큐리티 세션으로 처리
        HttpSession session = httpServletRequest.getSession();

        session.setAttribute( "profileImg",user.getProfileImg());
        session.setAttribute( "userName",user.getUsername());

        userRepository.save(user);

        //현재 IP를 받아온다.
        String ip = CommonUtil.getUserIp();

        //로그인 히스토리 기록
        LoginHistory loginHistory = LoginHistory.builder()
                .user(user)
                .isLogin(true)
                .ipChecked(IpChecked.UNCHECKED)
                .ip(ip)
                .loginDate(LocalDateTime.now())
                .build();

        //특정 유저 IP의 가장 최신 상태를 가지고 온다.
        LoginHistory temp = loginHistoryRepository.findTopByUserAndIpOrderByIdDesc(user, ip);

        if(temp != null){
            loginHistory.setIpChecked(temp.getIpChecked() );
        }

        loginHistoryRepository.save(loginHistory);


        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }



}
