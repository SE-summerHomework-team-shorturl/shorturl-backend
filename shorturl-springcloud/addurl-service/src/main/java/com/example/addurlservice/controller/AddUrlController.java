package com.example.addurlservice.controller;

import com.example.addurlservice.service.AddUrlService;
import com.example.redirectservice.service.RedirectService;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddUrlController {
    @Autowired
    private AddUrlService addUrlService;

    @GetMapping(value = "/short-url/add-to-my-short-urls")
    public Message addToMyShortUrls(@RequestParam(value = "url") String url) {
        return addUrlService.addToMyShortUrls(url);
    }
}
