package com.apt.proptech.admin.controller;

import com.apt.proptech.core.domain.enums.AssociateRound;
import com.apt.proptech.core.domain.enums.UserRole;
import com.apt.proptech.core.domain.enums.UserState;
import com.apt.proptech.core.service.AssociateService;
import com.apt.proptech.core.service.ReceiptService;
import com.apt.proptech.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String Dashboard(Model model){

        model.addAttribute("contentName","Dashboard");
        model.addAttribute("dashboardLayout", true);

        model.addAttribute("totalUserCount" , userService.getUserRoleAndExceptStateCount(UserRole.ROLE_USER, UserState.UN_AUTH ));

        model.addAttribute("totalAssociateCount" , associateService.getAssociateExceptRoundCount(AssociateRound.FINISH));

        model.addAttribute("totalPaid" , receiptService.getTotalPaid());

        model.addAttribute("totalTaskPercent" , associateService.getTotalTaskPercent(0));

        return "main";
    }
}
