package com.xatu.onlineedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xatu.onlineedu.entity.EduCourse;
import com.xatu.onlineedu.entity.vo.CoursePublishVo;
import com.xatu.onlineedu.entity.vo.CourseVo;
import com.xatu.onlineedu.exception.EduException;

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
}
