package com.example.shorturl.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.example.shorturl.util", "com.example.shorturl.shortener"})
public class ShortUrlShortenerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortUrlShortenerApplication.class, args);
    }
}
