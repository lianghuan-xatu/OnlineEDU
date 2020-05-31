package com.xatu.onlineedu.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.entity.EduVideo;
import com.xatu.onlineedu.exception.EduException;
import com.xatu.onlineedu.mapper.EduVideoMapper;
import com.xatu.onlineedu.service.EduVideoService;
import com.xatu.onlineedu.util.AliVodInitUtil;
import com.xatu.onlineedu.util.ConstantPropertiesUtil;
import com.xatu.onlineedu.util.ConstantVodPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-18
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    EduVideoMapper eduVideoMapper;

    @Override
    public boolean getCountByChapterId(String chapterId) {
        Integer count = eduVideoMapper.selectCount(new QueryWrapper<EduVideo>().eq("chapter_id", chapterId));
        return null != count && count > 0;
    }

    @Override
    public String uploadVideo(MultipartFile file) {


        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRECT,
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                log.warn(errorMessage);
                if (StringUtils.isEmpty(videoId)) {
                    throw new EduException(20001, errorMessage);
                }
            }

            return videoId;
        } catch (IOException e) {
            throw new EduException(20001, " vod 服务上传失败");

        }
    }

    @Override
    public Boolean deleteVideoInfoById(String videoId) {

        try{
            EduVideo eduVideo = eduVideoMapper.selectById(videoId);
            String videoSourceId = eduVideo.getVideoSourceId();

            DefaultAcsClient client = AliVodInitUtil.initVodClient(ConstantVodPropertiesUtil.ACCESS_KEY_ID,ConstantVodPropertiesUtil.ACCESS_KEY_SECRECT);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(videoSourceId);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
        }

        int i = eduVideoMapper.deleteById(videoId);
        if(i>0){
            return true;
        }

        return false;
    }

}
