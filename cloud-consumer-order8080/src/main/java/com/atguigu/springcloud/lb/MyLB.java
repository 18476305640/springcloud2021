package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component //第三步
public class MyLB implements LoadBalancer{
    private AtomicInteger atomicInteger = new AtomicInteger(0);
//    private int a = 1;
    //作用： 记录访问次数
//    private Object obj = new Object();
    public  int getAndIncrement() {
//        synchronized (obj) {
//            System.out.println("****a: "+(a++));
//        }
        int current;//0/1
        int next;//1/2
        do {
            current = this.atomicInteger.get(); //0-1/1-2
            next = current >= 2147483647 ? 0 : current+1;
        }while (! this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****next: "+next);
        return next;//1/2/3/4/5
    }


    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        //返回要访问的具体server
        return serviceInstances.get(index);
    }
}
