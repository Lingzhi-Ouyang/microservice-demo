package com.njoy.springcloud.dao;

import com.njoy.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ouyanglingzhi
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment queryById(@Param("id") Long id);
}
