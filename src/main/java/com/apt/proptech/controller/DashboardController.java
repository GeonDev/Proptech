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

        //세션은 단순 화면 표시용 기능 -> 실제 연산은 시큐리티 세션으로 처리
        session.setAttribute( "profileImg",principal.getUser().getProfileImg());
        session.setAttribute( "userName",principal.getUser().getUsername());

        model.addAttribute("contentName","Dashboard");
        model.addAttribute("dashboardLayout", true);

        model.addAttribute("totalUserCount" , userService.getUserRoleAndExceptState(UserRole.ROLE_USER, UserState.UN_AUTH ).size());

        model.addAttribute("totalAssociateCount" , associateService.getAssociateExceptRound(AssociateRound.INACTIVE).size());

        model.addAttribute("totalFund" , receiptService.getTotalPaid());


        return "main";
    }
}
