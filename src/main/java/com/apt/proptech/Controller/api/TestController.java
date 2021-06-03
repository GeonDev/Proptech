package com.apt.proptech.Controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    @RequestMapping("/testcon")
    public ModelAndView testCont(Model model){


        ModelAndView mv = new ModelAndView("jsonView");

        mv.addObject("name","Son");
        return mv;
    }

}
