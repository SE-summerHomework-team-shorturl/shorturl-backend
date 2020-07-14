package com.example.addurlservice.controller;

import com.example.addurlservice.service.AddUrlService;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddUrlController {
    @Autowired
    private AddUrlService addUrlService;

    @GetMapping(value = "/urlmanage/addurl")
    public Message addToMyShortUrls(@RequestParam(value = "url") String url,@RequestParam("userId") Integer id) {
        System.out.println(url);
        System.out.println(id);
        return addUrlService.addToMyShortUrls(url,id);
    }
}
