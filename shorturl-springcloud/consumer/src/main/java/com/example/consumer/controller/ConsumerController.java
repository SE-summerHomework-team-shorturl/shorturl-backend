package com.example.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    private static final String redirectServiceUrl = "http://REDIRECT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/print", method = RequestMethod.GET)
    public String print() {
        return restTemplate.getForObject(redirectServiceUrl+ "/print", String.class);
    }
}
