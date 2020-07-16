package com.example.urlshortener.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UrlShortenerEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerEurekaApplication.class, args);
    }
}
