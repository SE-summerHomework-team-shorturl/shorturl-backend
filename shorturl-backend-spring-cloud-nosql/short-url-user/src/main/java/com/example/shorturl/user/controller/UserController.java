package com.example.shorturl.user.controller;

import com.example.shorturl.user.entity.User;
import com.example.shorturl.user.service.UserService;
import com.example.shorturl.util.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/register")
    public Message register(@RequestBody User user) {
        return userService.register(user);
    }
}
