package com.example.shorturl.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate template;

    @GetMapping("/test")
    public void test(@RequestParam String key) {
        System.out.println(template.opsForHash().entries(key));
    }
}
