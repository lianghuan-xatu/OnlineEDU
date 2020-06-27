package com.xatu.onlineedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xatu.onlineedu.entity.Order;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-15
 */
@RestController
@RequestMapping("/eduOrder")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 根据课程id和用户id来创建订单
     *
     * @param courseId
     * @param request
     * @return
     */
    @PostMapping("/createOrder/{courseId}")
    public Result save(@PathVariable String courseId, HttpServletRequest request) {
        String orderId = orderService.saveOrder(courseId, request);
        return Result.success().data("orderId", orderId);
    }


    /**
     * 根据订单id获取订单信息
     */
    @GetMapping("/getOrderInfo/{orderId}")
    public Result getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderId);
        Order one = orderService.getOne(queryWrapper);
        return Result.success().data("orderInfo", one);
    }


    /**
     * 查询是否是购买课程
     * @param memberId
     * @param id
     * @return
     */
    @GetMapping("isBuyCourse/{memberId}/{id}")
    public boolean isBuyCourse(@PathVariable String memberId,@PathVariable String id) {
        int count = orderService.count(new QueryWrapper<Order>().eq("member_id", memberId).eq("course_id", id).eq("status", 1));
        if(count>0){
            return true;
        }else{
            return false;
        }
    }



}
