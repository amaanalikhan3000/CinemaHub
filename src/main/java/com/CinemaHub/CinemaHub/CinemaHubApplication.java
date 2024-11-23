package com.CinemaHub.CinemaHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableAutoConfiguration
public class CinemaHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaHubApplication.class, args);


    }

}
