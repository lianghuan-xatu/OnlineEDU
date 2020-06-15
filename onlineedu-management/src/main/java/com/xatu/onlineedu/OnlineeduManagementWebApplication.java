package com.xatu.onlineedu;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@MapperScan(basePackages = "com.xatu.onlineedu.mapper")
@EnableFeignClients
public class OnlineeduManagementWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineeduManagementWebApplication.class, args);
    }

}
