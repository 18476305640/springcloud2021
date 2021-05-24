package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //OpenFeign
public class OrderNacosMain8484 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain8484.class,args);
    }
}
