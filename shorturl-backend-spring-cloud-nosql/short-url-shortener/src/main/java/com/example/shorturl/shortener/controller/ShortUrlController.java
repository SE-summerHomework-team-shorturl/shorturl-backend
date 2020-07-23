package com.example.shorturl.shortener.controller;

import com.example.shorturl.shortener.service.ShortUrlService;
import com.example.shorturl.util.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortUrlController {
    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping(value = "/urlmanage/addurl")
    public Message addToMyShortUrls(@RequestParam(value = "url") String url) {
        return shortUrlService.addToMyShortUrls(url);
    }
}
