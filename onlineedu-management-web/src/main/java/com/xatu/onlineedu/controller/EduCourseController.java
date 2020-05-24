package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.entity.vo.CourseVo;
import com.xatu.onlineedu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-16
 */
@RestController
@RequestMapping("/eduCourse")
public class EduCourseController {
    @Autowired
    EduCourseService eduCourseService;

    @PostMapping("/saveCourseInfo")
    public Result saveCourseInfo(@RequestBody CourseVo courseInfo){
        String courseId = eduCourseService.saveCourseInfo(courseInfo);
        return Result.success().data("courseId",courseId);
    }



}

