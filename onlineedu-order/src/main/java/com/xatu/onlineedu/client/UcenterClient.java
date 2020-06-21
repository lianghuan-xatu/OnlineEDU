package com.xatu.onlineedu.client;

import com.xatu.onlineedu.entity.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {

    @GetMapping("/eduUcenter/getMemberInfoByMemberId/{memberId}")
    public UcenterMember getMemberInfoByMemberId(@PathVariable String memberId);
}
