package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    //正常访问
    @GetMapping("/consumer/payment/hystrix/OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id) {
        String info_ok = paymentHystrixService.paymentInfo_OK(id);
        return info_ok;
    }
    //延时访问
    @GetMapping("/consumer/payment/hystrix/Timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id) {
        String timeOut = paymentHystrixService.paymentInfo_TimeOut(id);
        return timeOut;
    }
}
