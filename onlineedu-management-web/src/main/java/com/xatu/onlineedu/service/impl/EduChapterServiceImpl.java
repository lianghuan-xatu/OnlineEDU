package com.xatu.onlineedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.entity.EduChapter;
import com.xatu.onlineedu.entity.EduVideo;
import com.xatu.onlineedu.entity.vo.ChapterVo;
import com.xatu.onlineedu.entity.vo.VideoVo;
import com.xatu.onlineedu.mapper.EduChapterMapper;
import com.xatu.onlineedu.mapper.EduVideoMapper;
import com.xatu.onlineedu.service.EduChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-18
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    EduChapterMapper eduChapterMapper;
    @Autowired
    EduVideoMapper eduVideoMapper;



    @Override
    public List<ChapterVo> getNestedListByCourseId(String courseId) {
        List<EduChapter> eduChapterList = eduChapterMapper.selectList(new QueryWrapper<EduChapter>().eq("course_id", courseId));
        List<EduVideo> eduVideos = eduVideoMapper.selectList(new QueryWrapper<EduVideo>().eq("course_id",courseId));
        List<ChapterVo> chapterVoList = new ArrayList<>();
        for (EduChapter eduChapter : eduChapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            for (EduVideo eduVideo : eduVideos) {
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    chapterVo.getVideoVoList().add(videoVo);
                }
            }
            chapterVoList.add(chapterVo);
        }
        return chapterVoList;
    }
}
