package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentHystrixService paymentHystrixService;
    @Value("${server.port}")
    private String serverPost;

    //正常访问
    @GetMapping("/payment/hystrix/OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id){
        return paymentHystrixService.paymentInfo_OK(id);
    }
    //延时访问
    @GetMapping("/payment/hystrix/Timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id){
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    //=========================服务熔断========================
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentCircuitBreaker(id);
        log.info("****result:"+result);
        return result;
    }


}
