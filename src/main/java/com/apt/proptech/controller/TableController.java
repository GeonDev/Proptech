package com.apt.proptech.controller;

import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.oauth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/table")
public class TableController {

    @GetMapping("/user-list")
    public String userTables(Model model , @AuthenticationPrincipal PrincipalDetails principal){

        model.addAttribute("profileImg",principal.getUser().getProfileImg() );
        model.addAttribute("userName",principal.getUser().getUsername() );

        model.addAttribute("totalAlerts","");
        model.addAttribute("totalMessages","");

        model.addAttribute("contentName","User List");

        model.addAttribute("tableContent","");

        return "main";
    }

    @GetMapping("/associate-list")
    public String associateTables(Model model , @AuthenticationPrincipal PrincipalDetails principal){

        model.addAttribute("profileImg",principal.getUser().getProfileImg() );
        model.addAttribute("userName",principal.getUser().getUsername() );

        model.addAttribute("totalAlerts","");
        model.addAttribute("totalMessages","");

        model.addAttribute("contentName","Associate List");

        model.addAttribute("tableContent","");

        return "main";
    }



}
