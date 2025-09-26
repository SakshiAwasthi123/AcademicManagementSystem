package com.company.ams.controller;

import com.company.ams.dto.request.RegisterUserRequest;
import com.company.ams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public  String registerUser(@RequestBody RegisterUserRequest request) {

     userService.registerUser(request);
     return "List of users";
    }
}