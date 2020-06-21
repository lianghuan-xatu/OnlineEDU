package com.xatu.onlineedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xatu.onlineedu.entity.Order;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-15
 */
public interface OrderService extends IService<Order> {


    String saveOrder(String courseId, HttpServletRequest request);
}
