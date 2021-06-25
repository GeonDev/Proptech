package com.apt.proptech.controller;

import com.apt.proptech.domain.dto.Pagination;
import com.apt.proptech.domain.dto.UserDto;
import com.apt.proptech.domain.oauth.PrincipalDetails;
import com.apt.proptech.service.AssociateService;
import com.apt.proptech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/table")
public class TableController {

    @Autowired
    private UserService userService;

    @Autowired
    private AssociateService associateService;


    @GetMapping("/user-list")
    public String userTables(Model model, @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 10) Pageable pageable,
                             @RequestParam(value = "type", required = false, defaultValue = "") String type,
                             @RequestParam(value = "value", required = false, defaultValue = "") String value){

        Pagination<UserDto> pagination =  userService.getItemList(pageable,type,value);

        //머스테치는 값이 존재하는지로 if를 체크 한다.
        if(pagination.isFirstPage() ){
            model.addAttribute("isFirstPage", true);
        }

        if(pagination.isLastPage() ){
            model.addAttribute("isLastPage", true);
        }


        model.addAttribute("contentName","User List");

        model.addAttribute("pages", pagination);

        model.addAttribute("tableLayout","true");

        model.addAttribute("tableUser","true");


        return "main";
    }

    @GetMapping("/associate-list")
    public String associateTables(Model model){


        model.addAttribute("contentName","Associate List");

        model.addAttribute("tableLayout","true");

        model.addAttribute("tableAssociate","true");

        return "main";
    }



}
