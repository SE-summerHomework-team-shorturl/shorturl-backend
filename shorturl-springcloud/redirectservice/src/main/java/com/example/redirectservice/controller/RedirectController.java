package com.example.redirectservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String print() {
        return new String("print");
    }
}
