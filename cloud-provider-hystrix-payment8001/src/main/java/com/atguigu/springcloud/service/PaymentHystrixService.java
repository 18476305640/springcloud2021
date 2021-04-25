package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")}
    )
    public String paymentInfo_TimeOut(Long id){
        int timeNum = 5000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNum);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：   "+Thread.currentThread().getName()+"   paymentInfo_Timeout id: "+id+"\t耗时(毫秒)"+timeNum;
    }

    //服务降级FallBack
    public String paymentInfo_TimeOutHandler(Long id){
        return "线程池：   "+Thread.currentThread().getName()+"   paymentInfo_TimeOutHandler id: "+id+"现访问人数过多或服务出错，请悄后再试 /(ㄒoㄒ)/~~";
    }
}
