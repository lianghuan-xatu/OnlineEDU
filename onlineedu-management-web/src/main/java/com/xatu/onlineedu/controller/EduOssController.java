package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.Result;
import com.xatu.onlineedu.service.EduOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/eduOss")
@CrossOrigin
public class EduOssController {

    @Autowired
    private EduOssService eduOssService;


    //头像上传
    @PostMapping("/uploadAvatar")
    @ResponseBody
    public Result uploadOssFile(MultipartFile file){

        //获取上传文件  返回上传oss的url
        String url = eduOssService.uploadFileAvatar(file);

        return Result.success().data("url",url);
    }




}
