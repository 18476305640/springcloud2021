package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;


    @Override
    public void creat(Order order) {
        log.info("-------->开始新建订单");
        orderDao.create(order);

        log.info("------>订单微服务开始调用库存，进行扣减数量");
        storageService.decrease(order.getProductId(), order.getCount());

        log.info("------>订单微服务开始调用账户，进行扣减钱");
        accountService.decrease(order.getUserId(), order.getMoney());

        log.info("------>修改订单的状态：0-->1， 1表示订单已完成");
        orderDao.update(order.getUserId(), 0);

        log.info("--->下订单结束了，O(∩_∩)O哈哈~");
    }
}
