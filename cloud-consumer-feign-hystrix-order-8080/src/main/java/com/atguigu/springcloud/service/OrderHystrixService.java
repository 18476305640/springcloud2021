package com.atguigu.springcloud.service;

import com.atguigu.springcloud.service.impl.OrderHystrixFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = OrderHystrixFallback.class)
public interface OrderHystrixService {
    //正常访问
    @GetMapping("/payment/hystrix/OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id);
    //延时访问
    @GetMapping("/payment/hystrix/Timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id);
}
