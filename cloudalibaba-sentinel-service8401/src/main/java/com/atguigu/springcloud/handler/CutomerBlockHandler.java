package com.atguigu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CutomerBlockHandler {
    public static CommonResult blockHandlerA(BlockException e) {
        return new CommonResult(4444,"与业务解构ockHandlerA的方法");

    }
    public static CommonResult blockHandlerB(BlockException e) {
        return new CommonResult(4444,"与业务解构ockHandlerB的方法");
    }
}
