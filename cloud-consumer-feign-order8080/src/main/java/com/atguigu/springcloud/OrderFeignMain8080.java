package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  //加入OpenFeign注解
public class OrderFeignMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain8080.class,args);
    }
}
