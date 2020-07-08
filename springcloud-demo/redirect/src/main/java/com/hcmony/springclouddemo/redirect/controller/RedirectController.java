package com.hcmony.springclouddemo.redirect.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

    @GetMapping(value = "/r/{token}")
    public String redirect(@PathVariable String token) throws Exception {
        return token;
    }
}
