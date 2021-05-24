package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.domain.CommonResult;
import com.atguigu.springcloud.alibaba.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class AccountServiceImpl implements AccountService {
    @Override
    public CommonResult decrease(Long userId, BigDecimal money) {
        return new CommonResult(4444,"扣除余额失败Fallback~_~");
    }
}
