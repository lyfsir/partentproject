package com.lyf.thirdpartyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LyfThirdPartyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyfThirdPartyServiceApplication.class, args);
    }

}
