package com.apt.proptech.admin.controller.api;

import com.apt.proptech.core.controller.BaseController;
import com.apt.proptech.core.domain.User;
import com.apt.proptech.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends BaseController<User> {

    @Autowired
    private UserService userService;





}
