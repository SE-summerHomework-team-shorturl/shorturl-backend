package com.example.redirectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.sharedentity")
public class RedirectserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedirectserviceApplication.class, args);
    }

}
