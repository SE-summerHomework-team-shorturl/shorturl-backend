package com.example.shorturl.controller;

import com.example.shorturl.dto.Message;
import com.example.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortUrlController {
    @Autowired private ShortUrlService shortUrlService;

    @GetMapping(value = "/short-url/add-to-my-short-urls")
    public Message addToMyShortUrls(@RequestParam(value = "url") String url) {
        return shortUrlService.addToMyShortUrls(url);
    }

    @GetMapping(value = "/short-url/find-all-my-short-urls")
    public Message findAllMyShortUrls(@RequestParam(value = "page") int page,
                                      @RequestParam(value = "size") int size) {
        return shortUrlService.findAllMyShortUrls(page, size);
    }

    @GetMapping(value = "/short-url/delete-my-short-url-by-id")
    public Message deleteMyShortUrlById(@RequestParam(value = "id") int id) {
        return shortUrlService.deleteMyShortUrlById(id);
    }
}
