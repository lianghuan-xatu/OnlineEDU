package com.xatu.onlineedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.entity.EduChapter;
import com.xatu.onlineedu.entity.EduCourse;
import com.xatu.onlineedu.entity.EduCourseDescription;
import com.xatu.onlineedu.entity.EduVideo;
import com.xatu.onlineedu.entity.vo.CoursePublishVo;
import com.xatu.onlineedu.entity.vo.CourseVo;
import com.xatu.onlineedu.exception.EduException;
import com.xatu.onlineedu.mapper.EduCourseMapper;
import com.xatu.onlineedu.mapper.EduVideoMapper;
import com.xatu.onlineedu.service.EduChapterService;
import com.xatu.onlineedu.service.EduCourseDescriptionService;
import com.xatu.onlineedu.service.EduCourseService;
import com.xatu.onlineedu.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author
 * @since 2020-05-18
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    EduCourseMapper eduCourseMapper;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduVideoMapper eduVideoMapper;

    @Override
    public String saveCourseInfo(CourseVo courseInfoForm)  {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        eduCourse.setIsDeleted(0);
        int insert = eduCourseMapper.insert(eduCourse);
        if(insert==0){
            throw new EduException(2001,"课程插入失败");
        }

        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(cid);
        String description = courseInfoForm.getDescription();
        eduCourseDescription.setDescription(description);
        eduCourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public CourseVo getCoursInfoByCourseId(String courseId) {
        EduCourse eduCourse = eduCourseMapper.selectById(courseId);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getOne(new QueryWrapper<EduCourseDescription>().eq("id", courseId));
        CourseVo courseVo = new CourseVo();
        try{
            BeanUtils.copyProperties(eduCourse,courseVo);
        }catch (Exception e){
            throw new EduException(2001,"无数据");
        }

        courseVo.setDescription(eduCourseDescription.getDescription());
        return courseVo;
    }

    @Override
    public String updateCourseInfo(CourseVo courseInfo) {

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        eduCourse.setIsDeleted(0);
        int update = eduCourseMapper.update(eduCourse,new UpdateWrapper<EduCourse>().eq("id",eduCourse.getId()));
        if(update==0){
            throw new EduException(2001,"课程更新失败");
        }

        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(cid);
        String description = courseInfo.getDescription();
        eduCourseDescription.setDescription(description);
        eduCourseDescriptionService.update(eduCourseDescription,new UpdateWrapper<EduCourseDescription>().eq("id",cid));
        return eduCourse.getId();
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return eduCourseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    public Boolean removeDataById(String courseId) {
        try{
            eduCourseMapper.deleteById(courseId);
            eduCourseDescriptionService.removeById(courseId);
            eduChapterService.remove(new QueryWrapper<EduChapter>().eq("course_id",courseId));
            eduVideoService.remove(new QueryWrapper<EduVideo>().eq("course_id",courseId));
            List<EduVideo> eduVideos = eduVideoMapper.selectList(new QueryWrapper<EduVideo>().eq("course_id", courseId).select("id"));
            for (EduVideo eduVideo : eduVideos) {
                String id = eduVideo.getId();
                if(StringUtils.isNotEmpty(id)){
                    eduVideoService.deleteVideoInfoById(id);
                }
            }

        }catch (Exception e){
            return false;
        }
        return true;
    }
}
