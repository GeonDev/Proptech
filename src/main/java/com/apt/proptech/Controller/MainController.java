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

}
