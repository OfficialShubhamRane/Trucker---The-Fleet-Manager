package com.example.readingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@SpringBootApplication
public class ReadingServiceApplication {

    public static void main(String[] args) { SpringApplication.run(ReadingServiceApplication.class, args); }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
