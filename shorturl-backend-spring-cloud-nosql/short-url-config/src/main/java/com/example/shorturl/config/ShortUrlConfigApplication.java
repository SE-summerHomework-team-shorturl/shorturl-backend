package com.example.shorturl.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ShortUrlConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortUrlConfigApplication.class, args);
	}

}
