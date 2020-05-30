package com.xatu.onlineedu.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ConstantVodPropertiesUtil implements InitializingBean
{
    @Value("${aliyun.vod.file.keyid}")
    private String keyId;

    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;


    //定义公开静态常量
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRECT;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRECT = keySecret;
    }
}
