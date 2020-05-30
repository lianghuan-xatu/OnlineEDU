package com.xatu.onlineedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xatu.onlineedu.entity.EduCourse;
import com.xatu.onlineedu.entity.vo.CoursePublishVo;
import com.xatu.onlineedu.entity.vo.CourseQueryVo;
import com.xatu.onlineedu.entity.vo.CourseVo;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.EduCourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @GetMapping("/getPublishCourseInfo/{courseId}")
    public Result getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourseInfo(courseId);
        return Result.success().data("coursePublishInfo",coursePublishVo);
    }


    @PostMapping("/publishCourse/{courseId}")
    public Result publishCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        boolean b = eduCourseService.updateById(eduCourse);
        if(b){
            return Result.success();
        }
        return Result.fail();
    }

    @PostMapping("/getCourseListPage/{page}/{limit}")
    public Result getCourseListPage(@PathVariable long page, @PathVariable long limit, @RequestBody CourseQueryVo courseQueryVo){
        Page queryPage = new Page();
        queryPage.setCurrent(page);
        queryPage.setSize(limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(courseQueryVo.getTitle())){
            queryWrapper.like("title",courseQueryVo.getTitle());
        }
        if(courseQueryVo.getStatus()!=null){
            queryWrapper.eq("status",courseQueryVo.getStatus());
        }
        if(StringUtils.isNotEmpty(courseQueryVo.getBegin())){
            queryWrapper.ge("gmt_create",courseQueryVo.getBegin());
        }
        if(StringUtils.isNotEmpty(courseQueryVo.getEnd())){
            queryWrapper.le("gmt_create",courseQueryVo.getEnd());
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");
        eduCourseService.page(queryPage, queryWrapper);
        long total = queryPage.getTotal();
        List records = queryPage.getRecords();
        return Result.success().data("total",total).data("rows",records);
    }


    @DeleteMapping("/deleteDataById/{courseId}")
    public Result deleteDataById(@PathVariable String courseId){
        Boolean success = eduCourseService.removeDataById(courseId);
        if(success){
            return Result.success();
        }
        return Result.fail();
    }




}

