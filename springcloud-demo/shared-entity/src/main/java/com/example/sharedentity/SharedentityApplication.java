package com.example.sharedentity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SharedentityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedentityApplication.class, args);
    }

}
