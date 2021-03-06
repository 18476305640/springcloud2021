package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.OrderHystrixService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class OrderHystrixFallback implements OrderHystrixService {

    @Override
    public String paymentInfo_OK(Long id) {
        return "---OrderHystrixFallback fall back-paymentInfo_OK,/(ćoć)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        return "---OrderHystrixFallback fall back-paymentInfo_TimeOut,/(ćoć)/~~";
    }


}
