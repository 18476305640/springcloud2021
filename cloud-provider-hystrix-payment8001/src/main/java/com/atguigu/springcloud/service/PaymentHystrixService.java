package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    //==============================服务熔断================================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗日期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("**********id不能为负数***********");
        }
        String serialNnmber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t调用成功，流水号："+serialNnmber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能为复数，请稍后再试，/(ㄒoㄒ)/~~    id:"+id;
    }
}
