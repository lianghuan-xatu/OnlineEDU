package com.xatu.onlineedu.client;


import com.xatu.onlineedu.entity.UcenterMember;
import com.xatu.onlineedu.exception.EduException;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient {

    @Override
    public UcenterMember getMemberInfoByMemberId(String memberId) {
        throw new EduException(2001,"timeout");
    }
}
