package com.apt.proptech.hendler;

import com.apt.proptech.domain.LoginHistory;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.IpChecked;
import com.apt.proptech.repository.LoginHistoryRepository;
import com.apt.proptech.repository.UserRepository;
import com.apt.proptech.service.UserService;
import com.apt.proptech.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        User user = userRepository.findByUsername(request.getParameter("username") );

        if(user!=null){
            user.setFailLoginCount(user.getFailLoginCount()+1 );
            userRepository.save(user);

            //현재 IP를 받아온다.
            String ip = CommonUtil.getUserIp();

            //로그인 히스토리 기록
            LoginHistory loginHistory = LoginHistory.builder()
                    .user(user)
                    .isLogin(false)
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

        }

        String msg = "Invaild Username or Password";

        if(exception instanceof BadCredentialsException){
            //비밀번호 오류
            msg = "Invalid Password";
        }else if(exception instanceof InsufficientAuthenticationException){
            //oauth 토큰 발급 오류
            msg = "Invalid Secret Key";
        }else if(exception instanceof AuthenticationCredentialsNotFoundException){
            //계정 인증 실패
            msg = "Reject Authentication";
        }else if(exception instanceof LockedException){
            //계정 잠김( 계정 탈퇴 상태)
            msg = "Locked Account(Retired) ";
        }else if(exception instanceof DisabledException){
            //계정 잠김 (로그실 실패 횟수 초과)
            msg = "Locked Account(login Fail Count Over)";
        }

        setDefaultFailureUrl("/login?error=true&exception="+msg);

        super.onAuthenticationFailure(request,response,exception);
    }
}
