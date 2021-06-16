package com.apt.proptech.controller;

import com.apt.proptech.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User>{

}
