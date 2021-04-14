//PaymentService
package com.atguigu.springcloud.entities.service;
import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
