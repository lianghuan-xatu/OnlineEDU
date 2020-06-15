package com.xatu.onlineedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.entity.EduChapter;
import com.xatu.onlineedu.entity.EduCourse;
import com.xatu.onlineedu.entity.EduCourseDescription;
import com.xatu.onlineedu.entity.EduVideo;
import com.xatu.onlineedu.entity.vo.CourseFrontQueryVo;
import com.xatu.onlineedu.entity.vo.CoursePublishVo;
import com.xatu.onlineedu.entity.vo.CourseVo;
import com.xatu.onlineedu.entity.vo.CourseWebVo;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<EduCourse> selectByTeacherId(String id) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper();
        queryWrapper.eq("teacher_id",id);
        //按照更新时间倒序排序
        queryWrapper.orderByDesc("gmt_modified");
        List<EduCourse> eduCourses = eduCourseMapper.selectList(queryWrapper);
        return eduCourses;
    }

    @Override
    public Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseFrontQueryVo courseQuery) {

            QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
                queryWrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
            }

            if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
                queryWrapper.eq("subject_id", courseQuery.getSubjectId());
            }

            if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
                queryWrapper.orderByDesc("buy_count");
            }

            if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
                queryWrapper.orderByDesc("gmt_create");
            }

            if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {
                queryWrapper.orderByDesc("price");
            }

            baseMapper.selectPage(pageParam, queryWrapper);

            List<EduCourse> records = pageParam.getRecords();
            long current = pageParam.getCurrent();
            long pages = pageParam.getPages();
            long size = pageParam.getSize();
            long total = pageParam.getTotal();
            boolean hasNext = pageParam.hasNext();
            boolean hasPrevious = pageParam.hasPrevious();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("items", records);
            map.put("current", current);
            map.put("pages", pages);
            map.put("size", size);
            map.put("total", total);
            map.put("hasNext", hasNext);
            map.put("hasPrevious", hasPrevious);

            return map;
        }

    @Override
    public CourseWebVo selectInfoWebById(String courseId) {
            this.updatePageViewCount(courseId);
            return baseMapper.selectInfoWebById(courseId);
    }


    @Override
    public void updatePageViewCount(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        eduCourse.setViewCount(eduCourse.getViewCount() + 1);
        baseMapper.updateById(eduCourse);
    }



}
