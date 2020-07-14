package com.example.userurlservice.controller;

import com.example.sharedentity.dto.Message;
import com.example.userurlservice.service.UserUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserUrlController {
    @Autowired
    private UserUrlService userUrlService;

    @GetMapping(value = "/urlmanage/findurl")
    public Message findAllMyShortUrls(@RequestParam(value = "page") int page,
                                      @RequestParam(value = "size") int size,
                                      @RequestParam(value = "userId") int userId) {
        return userUrlService.findAllMyShortUrls(page, size,userId);
    }

    @GetMapping(value = "/urlmanage/deleteurl")
    public Message deleteMyShortUrlById(@RequestParam(value = "id") int id,
                                        @RequestParam(value = "userId") int userId) {
        return userUrlService.deleteMyShortUrlById(id,userId);
    }
}
