package com.xatu.onlineedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xatu.onlineedu.entity.EduCourse;
import com.xatu.onlineedu.entity.vo.CourseFrontQueryVo;
import com.xatu.onlineedu.entity.vo.CoursePublishVo;
import com.xatu.onlineedu.entity.vo.CourseVo;
import com.xatu.onlineedu.entity.vo.CourseWebVo;
import com.xatu.onlineedu.exception.EduException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-16
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseVo courseInfoForm) throws EduException;

    CourseVo getCoursInfoByCourseId(String courseId);

    String updateCourseInfo(CourseVo courseInfo);

    CoursePublishVo getPublishCourseInfo(String courseId);

    Boolean removeDataById(String courseId);

    List<EduCourse> selectByTeacherId(String id);

    Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseFrontQueryVo courseQuery);

    CourseWebVo selectInfoWebById(String courseId);
}
