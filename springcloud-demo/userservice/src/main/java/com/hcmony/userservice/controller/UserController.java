package com.hcmony.userservice.controller;


import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import com.hcmony.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired private UserService userService;

    @PostMapping(value = "/user/register")
    public Message register(@RequestBody User user) {
        return userService.register(user);
    }
}
