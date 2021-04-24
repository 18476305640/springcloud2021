package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixService {
    /**
     * 正常访问
     */
    public String paymentInfo_OK(Long id) {
        return "线程池： "+Thread.currentThread().getName()+"   paymentInfo_OK id: "+id+"\t哈哈";
    }
    /**
     * 模拟出错
     */
    public String paymentInfo_TimeOut(Long id){
        int timeNum = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：   "+Thread.currentThread().getName()+"   paymentInfo_Timeout id: "+id+"\t耗时"+timeNum+"秒钟";
    }
}
