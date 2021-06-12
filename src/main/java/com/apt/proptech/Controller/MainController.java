package com.apt.proptech.Controller;

import com.apt.proptech.domain.User;
import com.apt.proptech.service.UserService;
import com.apt.proptech.domain.oauth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

@Controller
public class MainController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String index(Model model ){
        model.addAttribute("message", "HELLOW!!! ");
        return "index";
    }

    @GetMapping("/login")
    public String loginView( ){

        return "login/login";
    }

    @GetMapping("/register")
    public String registerView( ){

        return "login/register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("User") User user ){

        System.out.println("ddddd "+user.getName() );

        userService.addItem(user);

        return "redirect:/";
    }


    @GetMapping("/userinfo")
    public @ResponseBody
    String user(@AuthenticationPrincipal PrincipalDetails principal) {
        System.out.println("Principal : " + principal.getUsername());
        // iterator 순차 출력 해보기
        Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
        while (iter.hasNext()) {
            GrantedAuthority auth = iter.next();
            System.out.println(auth.getAuthority());
        }

        return "유저 페이지입니다.";
    }



    @GetMapping("/forgot")
    public String forgotPasswordView( ){

        return "login/forgot-password";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}


