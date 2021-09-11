package com.apt.proptech.core.controller;

import com.apt.proptech.core.domain.User;
import com.apt.proptech.core.domain.dto.UserDto;
import com.apt.proptech.core.domain.enums.IpChecked;
import com.apt.proptech.core.domain.oauth.PrincipalDetails;
import com.apt.proptech.core.service.AccountService;
import com.apt.proptech.core.service.LoginHistoryService;
import com.apt.proptech.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/toolbar")
public class ToolBarController {

    static final Logger LOGGER = LoggerFactory.getLogger(ToolBarController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoginHistoryService loginHistoryService;



    @GetMapping(value = "/user-detail")
    public String userDetailView(@AuthenticationPrincipal PrincipalDetails principal, Model model){

        User user = principal.getUser();

        model.addAttribute("info", new UserDto(user));


        if(user.getCompany() !=null ){
            model.addAttribute("isCompany" , true );
        }

        model.addAttribute("accountList" , accountService.getAccountInfo(user) );

        //금지된 IP를 제외한 전체 출력
        model.addAttribute("ipList" , loginHistoryService.getLoginHistoryExceptIpChecked(user, IpChecked.BANNED , 10) );

        model.addAttribute("historyList" , loginHistoryService.getLoginHistoryInfo(user) );

        model.addAttribute("contentName","Profile");
        model.addAttribute("profileLayout", true);


        return "main";
    }

    @PostMapping("/user-update")
    public String register( @RequestParam(value="image", required=false) List<MultipartFile> files,
                            @ModelAttribute("user") User user ){


        userService.addItem(user);

        return  "redirect:/toolbar/user-detail";
    }

    @GetMapping("/delete-his")
    public String deleteLoginHistory(@RequestParam(value="id", required=false) String id){

        if(id !=null ){
            loginHistoryService.deleteLoginHistory(Long.parseLong(id));
        }
        //화면 초기화
        return  "redirect:/toolbar/user-detail";
    }


}