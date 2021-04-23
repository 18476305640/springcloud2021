package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
//第二步
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
