package com.xatu.onlineedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xatu.onlineedu.entity.PayLog;

import java.util.Map;

public interface PayLogService extends IService<PayLog>
{
    Map createNative(String orderNo);

    Map<String, String> queryStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
