package com.xatu.onlineedu.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.xatu.onlineedu.exception.EduException;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.EduVideoService;
import com.xatu.onlineedu.util.AliVodInitUtil;
import com.xatu.onlineedu.util.ConstantVodPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/eduVod")
public class EduVodController {


    @Autowired
    private EduVideoService eduVideoService;

    @RequestMapping("/uploadVideo")
    public Result uploadVideo(@RequestParam("file") MultipartFile file){

                String videoId = eduVideoService.uploadVideo(file);
                return Result.success().message("视频上传成功").data("videoId", videoId);
    }



    @DeleteMapping("/removeVideo/{videoId}")
    public Result removeVideo(@PathVariable String videoId){
        try{
            //初始化对象
            DefaultAcsClient client = AliVodInitUtil.initVodClient(ConstantVodPropertiesUtil.ACCESS_KEY_ID,ConstantVodPropertiesUtil.ACCESS_KEY_SECRECT);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(videoId);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return Result.success();

        }catch (Exception e){
            e.printStackTrace();
            throw new EduException(2001,"删除视频失败");
        }
    }




        @GetMapping("get-play-auth/{videoId}")
        public Result getVideoPlayAuth(@PathVariable("videoId") String videoId) throws Exception {

            //获取阿里云存储相关常量
            String accessKeyId = ConstantVodPropertiesUtil.ACCESS_KEY_ID;
            String accessKeySecret = ConstantVodPropertiesUtil.ACCESS_KEY_SECRECT;

            //初始化
            DefaultAcsClient client = AliVodInitUtil.initVodClient(accessKeyId, accessKeySecret);

            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);

            //响应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            //得到播放凭证
            String playAuth = response.getPlayAuth();

            //返回结果
            return Result.success().message("获取凭证成功").data("playAuth", playAuth);
        }



}
