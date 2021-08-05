package com.apt.proptech.controller.api;

import com.apt.proptech.controller.BaseController;
import com.apt.proptech.domain.User;
import com.apt.proptech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends BaseController<User> {

    @Autowired
    private UserService userService;





}
