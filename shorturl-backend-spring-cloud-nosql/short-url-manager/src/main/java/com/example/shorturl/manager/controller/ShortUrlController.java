package com.example.shorturl.manager.controller;

import com.example.shorturl.manager.service.ShortUrlService;
import com.example.shorturl.util.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortUrlController {
    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping(value = "/urlmanage/findurl")
    public Message findAllMyShortUrls(@RequestParam(value = "page") int page,
                                      @RequestParam(value = "size") int size) {
        return shortUrlService.findAllMyShortUrls(page, size);
    }

    @GetMapping(value = "/urlmanage/deleteurl")
    public Message deleteMyShortUrlById(@RequestParam(value = "id") long id) {
        return shortUrlService.deleteMyShortUrlById(id);
    }
}
