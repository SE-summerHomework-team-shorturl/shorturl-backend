package com.hcmony.userservice.controller;

import com.example.sharedentity.dto.Message;
import com.hcmony.userservice.service.ShorturlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class ShorturlController {
    @Autowired private ShorturlService shorturlService;

    @GetMapping(value = "/shorturl/add-to-my-shorturls")
    public Message addToMyShorturls(@RequestParam String url) {
        return shorturlService.addToMyShorturls(url);
    }

    @GetMapping(value = "/shorturl/find-all-my-shorturls")
    public Message findAllMyShorturls() {
        return shorturlService.findAllMyShorturls();
    }

    @GetMapping(value = "/shorturl/delete-my-shorturl-by-id")
    public Message deleteMyShorturlById(@RequestParam int id) {
        return shorturlService.deleteMyShorturlById(id);
    }
}
