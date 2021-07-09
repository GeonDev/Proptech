package com.apt.proptech.hendler;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

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
            //계정 잠김
            msg = "Locked Account";
        }

        setDefaultFailureUrl("/login?error=true&exception="+msg);

        super.onAuthenticationFailure(request,response,exception);
    }
}
