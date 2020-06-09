package com.xatu.onlineedu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xatu.onlineedu.service.MsmService;
import com.xatu.onlineedu.util.ConstantPropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phone, String code, Map<String, Object> param) {


                if(StringUtils.isEmpty(phone)) return false;

                String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
                String secret = ConstantPropertiesUtil.ACCESS_KEY_SECRECT;

                DefaultProfile profile =
                        DefaultProfile.getProfile("default", accessKeyId, secret);
                IAcsClient client = new DefaultAcsClient(profile);

                CommonRequest request = new CommonRequest();
                //request.setProtocol(ProtocolType.HTTPS);
                request.setMethod(MethodType.POST);
                request.setDomain("dysmsapi.aliyuncs.com");
                request.setVersion("2017-05-25");
                request.setAction("SendSms");

                request.putQueryParameter("PhoneNumbers", phone);
                request.putQueryParameter("SignName",
                        "GL在线教育验证服务");
                request.putQueryParameter("TemplateCode", code);
                request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

                try {
                    CommonResponse response = client.getCommonResponse(request);
                    System.out.println(response.getData());
                    return response.getHttpResponse().isSuccess();
                } catch (ServerException e) {
                    e.printStackTrace();
                } catch (ClientException e) {
                    e.printStackTrace();
                }

        return false;
    }
}
