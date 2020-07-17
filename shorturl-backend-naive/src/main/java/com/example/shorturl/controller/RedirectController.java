package com.example.shorturl.controller;

import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {
    @Autowired
    private RedirectService redirectService;

    @RequestMapping(value = "/r/{token}")
    public void redirect(HttpServletResponse response, @PathVariable String token) throws Exception {
        ShortUrl shortUrl = redirectService.findShortUrlByToken(token);
        if (shortUrl != null)
            response.sendRedirect(shortUrl.getUrl());
        else
            response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
