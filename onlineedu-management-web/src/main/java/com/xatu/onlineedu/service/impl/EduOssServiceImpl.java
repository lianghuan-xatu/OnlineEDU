package com.xatu.onlineedu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xatu.onlineedu.service.EduOssService;
import com.xatu.onlineedu.util.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class EduOssServiceImpl implements EduOssService {

    /**
     * 上传头像到oss
     * @param file
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRECT;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        String uuid = UUID.randomUUID().toString();
        uuid.replaceAll("-","");

        //把文件按照日期分类
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String formatDate = simpleDateFormat.format(date);
        String fileName = formatDate+"/"+uuid+file.getOriginalFilename();
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = null;

        try{
            inputStream = file.getInputStream();
        // 上传文件流
        //第一个参数 Bucket名称      第二个参数    上传到oss文件路径和文件名称  /aa/bb/1.jpg
        ossClient.putObject(bucketName,fileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        }catch (Exception e){
            e.printStackTrace();
    }
        String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
        return url;
    }
}
