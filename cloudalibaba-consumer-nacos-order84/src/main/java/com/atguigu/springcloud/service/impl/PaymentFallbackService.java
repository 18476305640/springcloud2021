package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    //如果nacos-payment-consumer服务中的相应接口出事了，我来兜底
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult(444,"服务降级返回---PaymentFallbackService", new Payment(id, "errorSerial...."));
    }


}