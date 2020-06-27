package com.xatu.onlineedu.client;

import com.xatu.onlineedu.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    /**
     * 统计每天注册人数
     */
    @GetMapping(value = "/eduUcenter/registerCount/{day}")
    public Result registerCount(@PathVariable String day);
}
