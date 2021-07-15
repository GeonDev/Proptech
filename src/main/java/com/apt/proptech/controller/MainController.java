package com.apt.proptech.controller;

import com.apt.proptech.domain.User;
import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.service.UserService;
import com.apt.proptech.domain.oauth.PrincipalDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String index(){
        return "redirect:/dashboard/main";
    }

    @GetMapping("/login")
    public String loginView(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception){

        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        model.addAttribute("loginLayout" , true);
        return "login/login";
    }

    @GetMapping("/register")
    public String registerView(Model model ){

        model.addAttribute("regLayout", true);
        return "login/register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("User") User user ){
        LOGGER.debug("회원 가입 진행");
        user.setPassword(user.getPassword());
        user.setUserRole(UserRole.ROLE_USER);
        userService.addItem(user);

        return "redirect:/";
    }


    @GetMapping("/forgot")
    public String forgotPasswordView( ){
        return "login/forgot-password";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        //트리거 삭제
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        
        //세션삭제
        session.invalidate();

        return "redirect:/login";
    }


}


