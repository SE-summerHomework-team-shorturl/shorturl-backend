package com.example.registerservice.controller;

import com.example.registerservice.service.RegisterService;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired private RegisterService registerService;

    @PostMapping(value = "/user/register")
    public Message register(@RequestBody MultiValueMap<String, String> map)
    {
        User user = new User();
        user.setEmail(map.get("email").get(0));
        user.setUsername(map.get("username").get(0));
        user.setPassword(map.get("password").get(0));
        return registerService.register(user);
    }
}
