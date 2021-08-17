package com.apt.proptech.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error404";

            } else if(statusCode == HttpStatus.FORBIDDEN.value()){
                return "error/error403";

            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "error/error500";
            }

        }else{
            //강제로 에러페이지에 접근하려는 경우 404 출력
            return "error/error404";
        }

        return "error/errorDefault";
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
