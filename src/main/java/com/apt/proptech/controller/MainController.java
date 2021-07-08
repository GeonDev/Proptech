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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String index(){
        return "redirect:/main";
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.ROLE_USER);
        userService.addItem(user);

        return "redirect:/";
    }


    @GetMapping("/userinfo")
    @ResponseBody
    public String user(@AuthenticationPrincipal PrincipalDetails principal) {
        LOGGER.debug("principal : "+principal.getName());

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
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        //트리거 삭제
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        
        //세션삭제
        session.invalidate();

        return "redirect:/login";
    }

    @GetMapping("/main")
    public String mainDashboard(Model model , @AuthenticationPrincipal PrincipalDetails principal, HttpSession session){

        //세션은 단순 화면 표시용 기능 -> 실제 연산은 시큐리티 세션으로 처리
        session.setAttribute( "profileImg",principal.getUser().getProfileImg());
        session.setAttribute( "userName",principal.getUser().getUsername());

        //메시지 조회용으로 User ID 저장
        session.setAttribute( "userId",principal.getUser().getId());

        model.addAttribute("contentName","Main");

        return "main";
    }
}


