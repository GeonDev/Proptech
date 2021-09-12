package com.apt.admin.controller.api;



import com.apt.admin.controller.BaseController;
import com.apt.api.domain.User;
import com.apt.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends BaseController<User> {

    @Autowired
    private UserService userService;





}
