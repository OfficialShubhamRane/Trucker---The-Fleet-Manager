package com.example.detailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@EnableEurekaClient
@SpringBootApplication
public class DetailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DetailServiceApplication.class, args);
    }

}
