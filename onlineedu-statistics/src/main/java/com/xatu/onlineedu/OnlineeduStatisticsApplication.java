package com.xatu.onlineedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan(basePackages = "com.xatu.onlineedu.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class OnlineeduStatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineeduStatisticsApplication.class, args);
    }
}
