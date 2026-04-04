package com.workintech.Sprint19_Challenge.controller;


import com.workintech.Sprint19_Challenge.dto.RegisterUser;
import com.workintech.Sprint19_Challenge.entity.User;
import com.workintech.Sprint19_Challenge.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class AuthController {

    private AuthenticationService authService;

    @Autowired
    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping()
    public User register(@RequestBody RegisterUser registerUser) {
        return authService.register(registerUser.fullName(), registerUser.email(), registerUser.password());
    }
}
