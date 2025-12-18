package com.company.ams.controller;

import com.company.ams.dto.request.LoginRequest;
import com.company.ams.dto.request.RegisterUserRequest;
import com.company.ams.entity.UserEntity;
import com.company.ams.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public  UserEntity registerUser(@RequestBody UserEntity request) throws BadRequestException {
     return userService.registerUser(request);
    }

    @GetMapping("/fetchAll")
    public List<UserEntity> getAllUsers() {
        return userService.fetchAllUsers();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return  userService.userLogin(loginRequest);
    }
}

