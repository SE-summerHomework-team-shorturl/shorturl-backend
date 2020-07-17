package com.example.redirectservice.controller;

import com.example.redirectservice.service.RedirectService;
import com.example.sharedentity.entity.ShortUrl;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class RedirectController {
    static final public String err_Message_url_not_found="Short url not found";
    @Autowired
    private RedirectService redirectService;

    @RequestMapping(value = "/r/{token}")
    public void redirect(HttpServletResponse response, @PathVariable String token) throws Exception {
        ShortUrl shortUrl = redirectService.findShortUrlByToken(token);
        if(shortUrl!=null)
            response.sendRedirect(shortUrl.getUrl());
        else
            response.sendError(HttpStatus.SC_BAD_REQUEST,err_Message_url_not_found);
    }

  //  @RequestMapping(value = "/print")
   // public String print() {
  //      return "print";
   // }
}
