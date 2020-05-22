package com.xatu.onlineedu.service;

import org.springframework.web.multipart.MultipartFile;

public interface EduOssService
{
    String uploadFileAvatar(MultipartFile file);
}
