package com.apt.proptech.controller;

import com.apt.proptech.domain.dto.AssociateDto;
import com.apt.proptech.domain.dto.ColumnTitle;
import com.apt.proptech.domain.dto.Pagination;
import com.apt.proptech.domain.dto.UserDto;

import com.apt.proptech.service.AssociateService;
import com.apt.proptech.service.UserService;
import com.apt.proptech.util.ExcelDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.View;


import java.util.List;

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
                             @RequestParam(value = "start", required = false, defaultValue = "") String startDate,
                             @RequestParam(value = "end", required = false, defaultValue = "") String endDate,
                             @RequestParam(value = "selected", required = false, defaultValue = "first") String selected){

        Pagination<UserDto> pagination =  userService.getItemList(pageable, type, value,startDate, endDate);

        //페이지 이동 버튼 체크 -> 머스테치는 값이 존재하는지로 if를 체크 한다.
        if(pagination.isFirstPage() ){
            model.addAttribute("isFirstPage", true);
        }

        if(pagination.isLastPage() ){
            model.addAttribute("isLastPage", true);
        }


        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);

        //페이지 타이틀 이름
        model.addAttribute("contentName","User List");

        //페이지 콘텐츠를 포함한 DTO
        model.addAttribute("pages", pagination);

        //테이블 레이아웃을 불러옴
        model.addAttribute("tableLayout","true");

        //유저 테이블 형식을 불러옴
        model.addAttribute("tableUser","tableUser");

        //값이 빈값이면 전체 칼럼 선택
        if(selected.equals("first")){
            List<ColumnTitle> titleList = pagination.getColumnTitles();
            for(ColumnTitle title : titleList ){
                model.addAttribute(title.getOrder(), true );
            }
        }else{
            //선택된 칼럼만 활성화
            String[] selectArr = selected.split("-");
            for(String col : selectArr){
                model.addAttribute(col, true );
            }
        }




        return "main";
    }

    @GetMapping("/associate-list")
    public String associateTables (Model model, @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
                                   @RequestParam(value = "type", required = false, defaultValue = "") String type,
                                   @RequestParam(value = "value", required = false, defaultValue = "") String value,
                                   @RequestParam(value = "start", required = false, defaultValue = "") String startDate,
                                   @RequestParam(value = "end", required = false, defaultValue = "") String endDate,
                                   @RequestParam(value = "selected", required = false, defaultValue = "first") String selected){

        Pagination<AssociateDto> pagination =  associateService.getItemList(pageable,type,value,startDate, endDate);

        //페이지 이동 버튼 체크 -> 머스테치는 값이 존재하는지로 if를 체크 한다.
        if(pagination.isFirstPage() ){
            model.addAttribute("isFirstPage", true);
        }

        if(pagination.isLastPage() ){
            model.addAttribute("isLastPage", true);
        }


        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);

        model.addAttribute("contentName","Associate List");

        model.addAttribute("pages", pagination);

        model.addAttribute("tableLayout","true");

        model.addAttribute("tableAssociate","tableAssociate");

        //값이 빈값이면 전체 칼럼 선택
        if(selected.equals("first")){
            List<ColumnTitle> titleList = pagination.getColumnTitles();
            for(ColumnTitle title : titleList ){
                model.addAttribute(title.getOrder(), true );
            }
        }else{
            //선택된 칼럼만 활성화
            String[] selectArr = selected.split("-");
            for(String col : selectArr){
                model.addAttribute(col, true );
            }
        }

        return "main";
    }

    @GetMapping("/user-excel")
    public View userExcel(Model model,
                          @RequestParam(value = "type", required = false, defaultValue = "") String type,
                          @RequestParam(value = "value", required = false, defaultValue = "") String value,
                          @RequestParam(value = "start", required = false, defaultValue = "") String startDate,
                          @RequestParam(value = "end", required = false, defaultValue = "") String endDate){

        List<UserDto> data = userService.getExcelDate(type, value, startDate, endDate);
        model.addAttribute("data", data);

        //ExcelDownloader에서 어떤 데이터 인지 구분하기 위하여 전달
        model.addAttribute("contentType", "User");

        return new ExcelDownloader();
    }


    @GetMapping("/associate-excel")
    public View associateExcel(Model model,
                               @RequestParam(value = "type", required = false, defaultValue = "") String type,
                               @RequestParam(value = "value", required = false, defaultValue = "") String value,
                               @RequestParam(value = "start", required = false, defaultValue = "") String startDate,
                               @RequestParam(value = "end", required = false, defaultValue = "") String endDate){

        List<AssociateDto> data = associateService.getExcelDate(type, value, startDate, endDate);
        model.addAttribute("data", data);


        //ExcelDownloader에서 어떤 데이터 인지 구분하기 위하여 전달
        model.addAttribute("contentType", "Associate");

        return new ExcelDownloader();
    }

    @GetMapping(value = "/user-detail")
    public String userDetailView(@RequestParam(value = "id") String id , Model model){



        return "/contents/modal/modalUser";
    }

    @GetMapping(value = "/associate-detail")
    public String associateDetailView(@RequestParam(value = "id") String id , Model model){



        return "/contents/modal/modalAssociate";
    }

}
