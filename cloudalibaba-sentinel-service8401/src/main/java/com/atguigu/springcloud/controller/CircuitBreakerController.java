package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.handler.CutomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {
    @GetMapping("/fallback")
    @SentinelResource(value = "fallback",blockHandler = "myblockHandler",fallback = "myfallback")
    public CommonResult fallback() {
        int i = 1/0;
        return new CommonResult(200,"ok");
    }

    public CommonResult myblockHandler(BlockException e) {
        return new CommonResult(400,"float limit to blockHeadler!");
    }
    public CommonResult myfallback() {
        return new CommonResult(444,"error to fallback!");
    }
    //测试与代码解构的Block方法
    @GetMapping("/globalBlockHandler")
    @SentinelResource(value = "globalBlockHandler",
            blockHandlerClass = CutomerBlockHandler.class,
            blockHandler = "blockHandlerA")
    public CommonResult globalBlockHandler() {
        return new CommonResult(200,"ok~");
    }


 }