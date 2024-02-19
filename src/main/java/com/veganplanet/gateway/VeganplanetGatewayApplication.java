package com.veganplanet.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.veganplanet"})
public class VeganplanetGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeganplanetGatewayApplication.class, args);
    }

}
