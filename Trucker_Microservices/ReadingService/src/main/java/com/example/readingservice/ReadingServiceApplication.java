package com.example.readingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class ReadingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadingServiceApplication.class, args);
    }

}
