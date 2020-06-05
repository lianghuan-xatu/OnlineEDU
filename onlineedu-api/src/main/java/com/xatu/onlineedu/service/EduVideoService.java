package com.xatu.onlineedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xatu.onlineedu.entity.EduVideo;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-16
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean getCountByChapterId(String chapterId);

    String uploadVideo(MultipartFile file);

    Boolean deleteVideoInfoById(String videoId);
}
