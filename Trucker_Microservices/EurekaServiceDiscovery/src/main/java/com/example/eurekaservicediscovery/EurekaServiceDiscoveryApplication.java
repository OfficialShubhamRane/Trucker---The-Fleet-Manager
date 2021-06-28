package com.example.eurekaservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceDiscoveryApplication.class, args);
    }

}
