package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.entity.vo.CourseVo;
import com.xatu.onlineedu.result.Result;
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
@CrossOrigin
public class EduCourseController {
    @Autowired
    EduCourseService eduCourseService;

    @PostMapping("/saveCourseInfo")
    public Result saveCourseInfo(@RequestBody CourseVo courseInfo){
        String courseId = eduCourseService.saveCourseInfo(courseInfo);
        return Result.success().data("courseId",courseId);
    }


    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseVo courseInfo){
        String courseId = eduCourseService.updateCourseInfo(courseInfo);
        return Result.success().data("courseId",courseId);
    }


    @GetMapping("/getCourseInfoByCourseId/{courseId}")
    public Result getCourseInfoByCourseId(@PathVariable String courseId){
        CourseVo eduCourseVo = eduCourseService.getCoursInfoByCourseId(courseId);
        return Result.success().data("courseInfoVo",eduCourseVo);
    }



}

