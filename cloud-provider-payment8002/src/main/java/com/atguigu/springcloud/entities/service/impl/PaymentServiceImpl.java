package com.atguigu.springcloud.entities.service.impl;//PaymentServiceImpl
import com.atguigu.springcloud.entities.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.entities.service.PaymentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }
    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}
