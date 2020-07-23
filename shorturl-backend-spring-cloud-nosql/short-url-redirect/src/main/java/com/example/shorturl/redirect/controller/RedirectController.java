package com.example.shorturl.redirect.controller;

import com.example.shorturl.redirect.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class RedirectController {
    @Autowired
    private RedirectService redirectService;

    @RequestMapping(value = "/r/{token}")
    public void redirect(@PathVariable String token, HttpServletResponse response) throws IOException {
        String url = redirectService.findUrlByToken(token);

        if (url != null)
            response.sendRedirect(url);
        else
            response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
