package com.anescode.apgw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class apiGWApplication {
    public static void main(String[] args) {
        SpringApplication.run(apiGWApplication.class, args);
    }
}
