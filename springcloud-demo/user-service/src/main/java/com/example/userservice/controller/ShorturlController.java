package com.example.userservice.controller;


import com.example.sharedentity.dto.Message;
import com.example.userservice.service.ShorturlService;
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

    @GetMapping(value = "/short-url/add-to-my-short-urls")
    public Message addToMyShorturls(@RequestParam String url) {
        return shorturlService.addToMyShorturls(url);
    }

    @GetMapping(value = "/short-url/find-all-my-short-urls")
    public Message findAllMyShorturls() {
        return shorturlService.findAllMyShorturls();
    }

    @GetMapping(value = "/short-url/delete-my-short-url-by-id")
    public Message deleteMyShorturlById(@RequestParam int id) {
        return shorturlService.deleteMyShorturlById(id);
    }
}
