package com.example.redirectservice.controller;

import com.example.redirectservice.service.RedirectService;
import com.example.sharedentity.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {
    @Autowired
    private RedirectService redirectService;

    @RequestMapping(value = "/r/{token}")
    public String redirect(@PathVariable String token) throws Exception {
        ShortUrl shortUrl = redirectService.findShortUrlByToken(token);
        return shortUrl.getUrl();
    }
}
