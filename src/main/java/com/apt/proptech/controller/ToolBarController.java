package com.apt.proptech.controller;

import com.apt.proptech.domain.User;
import com.apt.proptech.domain.dto.UserDto;
import com.apt.proptech.service.AccountService;
import com.apt.proptech.service.LoginHistoryService;
import com.apt.proptech.service.LoginIpService;
import com.apt.proptech.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/toolbar")
public class ToolBarController {

    static final Logger LOGGER = LoggerFactory.getLogger(ToolBarController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoginIpService loginIpService;

    @Autowired
    private LoginHistoryService loginHistoryService;



    @GetMapping(value = "/user-detail")
    public String userDetailView(@RequestParam(value = "name") String userName , Model model){

        User user = userService.getItem(userName);

        model.addAttribute("info", new UserDto(user));


        if(user.getCompany() !=null ){
            model.addAttribute("isCompany" , true );
        }

        model.addAttribute("accountList" , accountService.getAccountInfo(user) );
        model.addAttribute("ipList" , loginIpService.getLoginIpInfo(user) );
        model.addAttribute("historyList" , loginHistoryService.getLoginHistoryInfo(user) );

        model.addAttribute("contentName","Profile");
        model.addAttribute("profileLayout", true);


        return "main";
    }

    @PostMapping("/user-update")
    public String register(@ModelAttribute("user") User user ){

        userService.addItem(user);

        return "redirect:/";
    }


}
