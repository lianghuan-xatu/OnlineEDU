package com.xatu.onlineedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xatu.onlineedu.entity.EduCourse;
import com.xatu.onlineedu.entity.vo.CoursePublishVo;
import com.xatu.onlineedu.entity.vo.CourseWebVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-18
 */
@Mapper
@Repository
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo selectInfoWebById(String courseId);
}
