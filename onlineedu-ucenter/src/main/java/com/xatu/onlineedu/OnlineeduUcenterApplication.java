package com.xatu.onlineedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "com.xatu.onlineedu.mapper")
@EnableDiscoveryClient
public class OnlineeduUcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineeduUcenterApplication.class, args);
    }

}
