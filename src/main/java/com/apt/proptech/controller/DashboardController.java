package com.apt.proptech.controller;

import com.apt.proptech.domain.enums.AssociateRound;
import com.apt.proptech.domain.enums.UserRole;
import com.apt.proptech.domain.enums.UserState;
import com.apt.proptech.domain.oauth.PrincipalDetails;
import com.apt.proptech.repository.ReceiptRepository;
import com.apt.proptech.service.AssociateService;
import com.apt.proptech.service.ReceiptService;
import com.apt.proptech.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AssociateService associateService;

    @Autowired
    private ReceiptService receiptService;


    @GetMapping("/main")
    public String Dashboard(Model model , @AuthenticationPrincipal PrincipalDetails principal, HttpSession session){

        model.addAttribute("contentName","Dashboard");
        model.addAttribute("dashboardLayout", true);

        model.addAttribute("totalUserCount" , userService.getUserRoleAndExceptStateCount(UserRole.ROLE_USER, UserState.UN_AUTH ));

        model.addAttribute("totalAssociateCount" , associateService.getAssociateExceptRoundCount(AssociateRound.INACTIVE));

        model.addAttribute("totalPaid" , receiptService.getTotalPaid());



        return "main";
    }
}
