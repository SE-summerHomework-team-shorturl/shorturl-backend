package com.example.shorturl.redirect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.shorturl.util", "com.example.shorturl.redirect"})
public class ShortUrlRedirectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortUrlRedirectApplication.class, args);
    }
}
