package com.apt.proptech.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

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

    @GetMapping("/forgot")
    public String forgotPasswordView( ){

        return "login/forgot-password";
    }

}
