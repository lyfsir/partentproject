package com.lyf.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LyfGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyfGatewayApplication.class, args);
    }

}
