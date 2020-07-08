package com.example.shorturl.controller;

import com.example.shorturl.dto.Message;
import com.example.shorturl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired private LoginService loginService;

    @GetMapping(value = "/login")
    public Message login() {
        return loginService.login();
    }
}
