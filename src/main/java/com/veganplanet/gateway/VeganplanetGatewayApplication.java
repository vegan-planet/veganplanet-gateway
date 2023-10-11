package com.veganplanet.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
public class VeganplanetGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeganplanetGatewayApplication.class, args);
    }

}
