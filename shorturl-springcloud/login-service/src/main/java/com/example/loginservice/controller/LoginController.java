package com.example.loginservice.controller;

import com.example.loginservice.service.LoginService;
import com.example.sharedentity.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login")
    public Message login() {
        return loginService.login();
    }
}
