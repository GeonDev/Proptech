package com.apt.proptech.controller;

import com.apt.proptech.domain.User;
import com.apt.proptech.domain.dto.AssociateDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.View;

import java.text.SimpleDateFormat;
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
                             @RequestParam(value = "selected", required = false, defaultValue = "c0-c1-c2-c3-c4-c5-c6-c7") String selected){

        //Pagination<UserDto> pagination =  userService.getItemList(pageable,type,value);
        Pagination<UserDto> pagination =  userService.getItemList(pageable, type, value,startDate, endDate);

        //페이지 이동 버튼 체크 -> 머스테치는 값이 존재하는지로 if를 체크 한다.
        if(pagination.isFirstPage() ){
            model.addAttribute("isFirstPage", true);
        }

        if(pagination.isLastPage() ){
            model.addAttribute("isLastPage", true);
        }

        if (startDate.equals("")){
            //오늘 날짜를 전달 (필터 초기값 세팅용)
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
            model.addAttribute("startDate",format.format(System.currentTimeMillis()));
        }else{
            model.addAttribute("startDate",startDate);
        }

        if (endDate.equals("")){
            //오늘 날짜를 전달 (필터 초기값 세팅용)
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
            model.addAttribute("endDate",format.format(System.currentTimeMillis()));
        }else{
            model.addAttribute("endDate",endDate);
        }


        //페이지 타이틀 이름
        model.addAttribute("contentName","User List");

        //페이지 콘텐츠를 포함한 DTO
        model.addAttribute("pages", pagination);

        //테이블 레이아웃을 불러옴
        model.addAttribute("tableLayout","true");

        //유저 테이블 형식을 불러옴
        model.addAttribute("tableUser","tableUser");

        //선택된 칼럼만 활성화
        String[] selectArr = selected.split("-");
        for(String col : selectArr){
            model.addAttribute(col, true );
        }

        return "main";
    }

    @GetMapping("/associate-list")
    public String associateTables (Model model, @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
                                   @RequestParam(value = "type", required = false, defaultValue = "") String type,
                                   @RequestParam(value = "value", required = false, defaultValue = "") String value,
                                   @RequestParam(value = "start", required = false, defaultValue = "") String startDate,
                                   @RequestParam(value = "end", required = false, defaultValue = "") String endDate,
                                   @RequestParam(value = "selected", required = false, defaultValue = "c0-c1-c2-c3-c4-c5-c6-c7-c8-c9") String selected){

        Pagination<AssociateDto> pagination =  associateService.getItemList(pageable,type,value);

        //페이지 이동 버튼 체크 -> 머스테치는 값이 존재하는지로 if를 체크 한다.
        if(pagination.isFirstPage() ){
            model.addAttribute("isFirstPage", true);
        }

        if(pagination.isLastPage() ){
            model.addAttribute("isLastPage", true);
        }


        if (startDate.equals("")){
            //오늘 날짜를 전달 (필터 초기값 세팅용)
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
            model.addAttribute("startDate",format.format(System.currentTimeMillis()));
        }else{
            model.addAttribute("startDate",startDate);
        }

        if (endDate.equals("")){
            //오늘 날짜를 전달 (필터 초기값 세팅용)
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
            model.addAttribute("endDate",format.format(System.currentTimeMillis()));
        }else{
            model.addAttribute("endDate",endDate);
        }


        model.addAttribute("contentName","Associate List");

        model.addAttribute("pages", pagination);

        model.addAttribute("tableLayout","true");

        model.addAttribute("tableAssociate","tableAssociate");

        String[] selectArr = selected.split("-");

        for(String col : selectArr){
            model.addAttribute(col, true );
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

}
