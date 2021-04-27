package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private OrderHystrixService paymentHystrixService;

    //正常访问
    @GetMapping("/consumer/payment/hystrix/OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id) {
        String info_ok = paymentHystrixService.paymentInfo_OK(id);
        return info_ok;
    }
    //延时访问
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/Timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id) {
        int i = 1/0;
        String timeOut = paymentHystrixService.paymentInfo_TimeOut(id);
        return timeOut;
    }
    public String paymentInfo_TimeOutHandler (@PathVariable("id") Long id) {
        return "我是消费者8080，对方制度系统繁忙，请10秒后再试，参数是"+id+"或者自己运行出错请自行检查，/(ㄒoㄒ)/~~";
    }
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

}
