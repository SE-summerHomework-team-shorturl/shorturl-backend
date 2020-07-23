package com.example.statisticservice;

import com.example.statisticservice.service.IReceiverService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(value={IReceiverService.class})

public class StatisticServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticServiceApplication.class, args);
    }

}
