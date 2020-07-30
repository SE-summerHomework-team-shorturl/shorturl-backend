package com.example.registerservice.controller;

import com.example.registerservice.service.RegisterService;
import com.example.sharedentity.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired private RegisterService registerService;

    @PostMapping(value = "/user/register")
    public Message register(@RequestBody Map<String, String> map)
    {
        return registerService.register(map);
    }
}
