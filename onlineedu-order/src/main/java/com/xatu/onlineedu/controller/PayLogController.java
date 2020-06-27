package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-15
 */
@RestController
@RequestMapping("/eduPayLog")
@CrossOrigin
public class PayLogController {


    @Autowired
    PayLogService payLogService;



    /**
     * 生成二维码
     * @param orderNo
     * @return
     */
    @GetMapping("/createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo){
        Map map = payLogService.createNative(orderNo);
        return Result.success().data("resultMap",map);
    }

    /**
     * 查询支付状态
     */
    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String,String> map =  payLogService.queryStatus(orderNo);
        if(map == null) {
            //出错
            return Result.fail().message("支付出错");
        }
        if(map.get("trade_state").equals("SUCCESS")) {
            //成功
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return Result.success().message("支付成功");
        }
        return Result.success().code(25000).message("支付中");
    }


}

