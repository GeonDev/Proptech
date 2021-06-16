package com.apt.proptech.controller;

import com.apt.proptech.domain.oauth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dash")
public class DashboardController {

    @GetMapping("/main")
    public String mainDashboard(Model model ,@AuthenticationPrincipal PrincipalDetails principal){

        model.addAttribute("profileImg",principal.getUser().getProfileImg() );
        model.addAttribute("userName",principal.getUser().getUsername() );

        model.addAttribute("totalAlerts","");
        model.addAttribute("totalMessages","");

        return "dash";
    }


}
