package com.apt.proptech.controller;

import com.apt.proptech.domain.dto.AssociateDto;
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
    public String userTables(Model model, @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
                             @RequestParam(value = "type", required = false, defaultValue = "") String type,
                             @RequestParam(value = "value", required = false, defaultValue = "") String value,
                             @RequestParam(value = "start", required = false, defaultValue = "") String StartDate,
                             @RequestParam(value = "end", required = false, defaultValue = "") String endDate,
                             @RequestParam(value = "selected", required = false, defaultValue = "c0#c1#c2#c3#c4#c5#c6#c7") String selected){

        Pagination<UserDto> pagination =  userService.getItemList(pageable,type,value);

        //페이지 이동 버튼 체크 -> 머스테치는 값이 존재하는지로 if를 체크 한다.
        if(pagination.isFirstPage() ){
            model.addAttribute("isFirstPage", true);
        }

        if(pagination.isLastPage() ){
            model.addAttribute("isLastPage", true);
        }


        //페이지 타이틀 이름
        model.addAttribute("contentName","User List");

        //페이지 콘텐츠를 포함한 DTO 
        model.addAttribute("pages", pagination);

        //테이블 레이아웃을 불러옴
        model.addAttribute("tableLayout","true");

        //유저 테이블 형식을 불러옴
        model.addAttribute("tableUser","tableUser");

        String[] selectArr = selected.split("#");

        //선택된 칼럼만 활성화
        for(int i =0; i<selectArr.length;i++){
            model.addAttribute(""+selectArr[i],true);
        }



        return "main";
    }

    @GetMapping("/associate-list")
    public String associateTables (Model model, @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
                                        @RequestParam(value = "type", required = false, defaultValue = "") String type,
                                        @RequestParam(value = "value", required = false, defaultValue = "") String value,
                                        @RequestParam(value = "start", required = false, defaultValue = "") String StartDate,
                                        @RequestParam(value = "end", required = false, defaultValue = "") String endDate,
                                        @RequestParam(value = "selected", required = false, defaultValue = "c0#c1#c2#c3#c4#c5#c6#c7#c8#c9") String selected){

        Pagination<AssociateDto> pagination =  associateService.getItemList(pageable,type,value);

        //페이지 이동 버튼 체크 -> 머스테치는 값이 존재하는지로 if를 체크 한다.
        if(pagination.isFirstPage() ){
            model.addAttribute("isFirstPage", true);
        }

        if(pagination.isLastPage() ){
            model.addAttribute("isLastPage", true);
        }

        model.addAttribute("contentName","Associate List");

        model.addAttribute("pages", pagination);

        model.addAttribute("tableLayout","true");

        model.addAttribute("tableAssociate","tableAssociate");

        String[] selectArr = selected.split("#");

        for(int i =0; i<selectArr.length;i++){
            model.addAttribute(""+selectArr[i],true);
        }

        return "main";
    }



}
