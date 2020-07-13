package com.example.consumer.controller;

import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ConsumerController {
    private static final String redirectServiceUrl = "http://REDIRECT-SERVICE";
    private static final String addUrlServiceUrl = "http://ADDURL-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/print")
    public String print()  {
        return "prints";
    }

    @RequestMapping(value = "/r/{token}")
    public void redirect(HttpServletResponse response, @PathVariable String token) throws Exception {
        String url = restTemplate.getForObject(redirectServiceUrl+ "/r/"+token, String.class);
        response.sendRedirect(url);
    }

    @GetMapping(value = "/short-url/add-to-my-short-urls")
    public Message addToMyShortUrls(@RequestParam(value = "url") String url) {
        return restTemplate.postForObject(redirectServiceUrl+ "/add-to-my-short-urls",url, Message.class);
    }
}
