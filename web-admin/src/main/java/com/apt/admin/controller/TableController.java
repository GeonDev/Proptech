package com.apt.admin.controller;

import com.apt.api.domain.Associate;
import com.apt.api.domain.User;
import com.apt.api.domain.dto.AssociateDto;
import com.apt.api.domain.dto.ColumnTitle;
import com.apt.api.domain.dto.Pagination;
import com.apt.api.domain.dto.UserDto;
import com.apt.api.domain.enums.IpChecked;
import com.apt.api.service.*;
import com.apt.api.util.ExcelDownloader;
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

import java.util.List;

@Controller
@RequestMapping("/table")
public class TableController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginHistoryService loginHistoryService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AssociateService associateService;

    @Autowired
    private PurchasePropService purchasePropService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private SalePropService salePropService;


    @GetMapping("/user-list")
    public String userTables(Model model, @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
                             @RequestParam(value = "type", required = false, defaultValue = "") String type,
                             @RequestParam(value = "value", required = false, defaultValue = "") String value,
                             @RequestParam(value = "start", required = false, defaultValue = "") String startDate,
                             @RequestParam(value = "end", required = false, defaultValue = "") String endDate,
                             @RequestParam(value = "selected", required = false, defaultValue = "first") String selected){

        Pagination<UserDto> pagination =  userService.getItemList(pageable, type, value,startDate, endDate);

        //????????? ?????? ?????? ?????? -> ??????????????? ?????? ?????????????????? if??? ?????? ??????.
        if(pagination.isFirstPage() ){
            model.addAttribute("isFirstPage", true);
        }

        if(pagination.isLastPage() ){
            model.addAttribute("isLastPage", true);
        }


        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);

        //????????? ????????? ??????
        model.addAttribute("contentName","User List");

        //????????? ???????????? ????????? DTO
        model.addAttribute("pages", pagination);

        //????????? ??????????????? ?????????
        model.addAttribute("tableLayout","true");

        //?????? ????????? ????????? ?????????
        model.addAttribute("tableUser","tableUser");

        //?????? ???????????? ?????? ?????? ??????
        if(selected.equals("first")){
            List<ColumnTitle> titleList = pagination.getColumnTitles();
            for(ColumnTitle title : titleList ){
                model.addAttribute(title.getOrder(), true );
            }
        }else{
            //????????? ????????? ?????????
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

        //????????? ?????? ?????? ?????? -> ??????????????? ?????? ?????????????????? if??? ?????? ??????.
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

        //?????? ???????????? ?????? ?????? ??????
        if(selected.equals("first")){
            List<ColumnTitle> titleList = pagination.getColumnTitles();
            for(ColumnTitle title : titleList ){
                model.addAttribute(title.getOrder(), true );
            }
        }else{
            //????????? ????????? ?????????
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

        //ExcelDownloader?????? ?????? ????????? ?????? ???????????? ????????? ??????
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


        //ExcelDownloader?????? ?????? ????????? ?????? ???????????? ????????? ??????
        model.addAttribute("contentType", "Associate");

        return new ExcelDownloader();
    }

    @GetMapping(value = "/user-detail")
    public String userDetailView(@RequestParam(value = "id") String id , Model model){

        User user = userService.getItem(Long.parseLong(id));

        model.addAttribute("info", new UserDto(user));


        if(user.getCompany() !=null ){
            model.addAttribute("isCompany" , true );
        }

        model.addAttribute("accountList" , accountService.getAccountInfo(user) );
        
        //????????? IP??? ????????? ?????? 10??? ??????
        model.addAttribute("ipList" , loginHistoryService.getLoginHistoryExceptIpChecked(user, IpChecked.BANNED, 10) );
        
        model.addAttribute("historyList" , loginHistoryService.getLoginHistoryInfo(user) );

        //?????? ????????? ????????? ?????????.
        return "/contents/modal/modalUser";
    }

    @GetMapping(value = "/associate-detail")
    public String associateDetailView(@RequestParam(value = "id") String id , Model model){

        //?????? ?????? ??????
        Associate associate = associateService.getItem(Long.parseLong(id));

        model.addAttribute("purchaseList" , purchasePropService.getBuyedPurchasList(associate) );

        model.addAttribute("propList", salePropService.getPropList(associate));

        model.addAttribute("staffList", staffService.getStaffList(associate));

        model.addAttribute("info", new AssociateDto(associate));


        //?????? ????????? ????????? ?????????.
        return "/contents/modal/modalAssociate";
    }

}
