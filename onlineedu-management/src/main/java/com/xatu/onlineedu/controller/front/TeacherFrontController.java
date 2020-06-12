package com.xatu.onlineedu.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xatu.onlineedu.entity.EduCourse;
import com.xatu.onlineedu.entity.EduTeacher;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.EduCourseService;
import com.xatu.onlineedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduFrontTeacher")
public class TeacherFrontController {

    @Autowired
    EduTeacherService eduTeacherService;
    @Autowired
    EduCourseService eduCourseService;

    @GetMapping("/pageList/{page}/{limit}")
    public Result pageList(@PathVariable long page,@PathVariable long limit) {
        Page<EduTeacher> teacherPage = new Page<>();
        teacherPage.setCurrent(page);
        teacherPage.setSize(limit);
        Map<String,Object> map = eduTeacherService.pageList(teacherPage);
        return Result.success().data("resultMap",map);

    }

    @RequestMapping("/getById/{id}")
    public Result getById(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        //根据讲师id查询出讲师讲的所有课程
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(id);
        return Result.success().data("teacher",teacher).data("courseList",courseList);
    }







}
