package com.njoy.springcloud.service;

import com.njoy.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author ouyanglingzhi
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment queryById(@Param("id") Long id);
}
