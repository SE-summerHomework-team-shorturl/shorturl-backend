package com.example.loginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.sharedentity","com.example.loginservice"})
@EntityScan(basePackages = {"com.example.sharedentity","com.example.loginservice"})
@EnableJpaRepositories(basePackages = {"com.example.sharedentity","com.example..loginservice"})
public class LoginserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginserviceApplication.class, args);
    }

}
