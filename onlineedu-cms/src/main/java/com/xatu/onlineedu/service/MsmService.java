package com.xatu.onlineedu.service;

import java.util.Map;

public interface MsmService {
    boolean send(String phone, String sms_180051135, Map<String, Object> param);
}
