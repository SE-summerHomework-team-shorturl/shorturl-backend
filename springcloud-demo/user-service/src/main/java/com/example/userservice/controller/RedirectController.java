package com.example.userservice.controller;


import com.example.sharedentity.entity.Shorturl;
import com.example.userservice.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {
    @Autowired private RedirectService redirectService;

    @RequestMapping(value = "/r/{token}")
    public void redirect(HttpServletResponse response, @PathVariable String token) throws Exception {
        Shorturl shorturl = redirectService.findShorturlByToken(token);
        response.sendRedirect(shorturl.getUrl());
    }
}
