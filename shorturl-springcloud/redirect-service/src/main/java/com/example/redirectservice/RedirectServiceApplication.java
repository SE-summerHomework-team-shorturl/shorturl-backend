package com.example.redirectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.sharedentity")
@EntityScan(basePackages = "com.example.sharedentity")
@EnableJpaRepositories(basePackages = "com.example.sharedentity")
public class RedirectserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedirectserviceApplication.class, args);
    }

}
