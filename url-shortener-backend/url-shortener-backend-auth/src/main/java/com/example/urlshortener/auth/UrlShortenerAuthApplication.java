package com.example.urlshortener.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableWebSecurity
@EnableAuthorizationServer
@EnableTransactionManagement
@EnableDiscoveryClient
public class UrlShortenerAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerAuthApplication.class, args);
    }
}
