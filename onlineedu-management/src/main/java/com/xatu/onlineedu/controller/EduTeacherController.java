package com.xatu.onlineedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.entity.EduTeacher;
import com.xatu.onlineedu.entity.vo.TeacherQuery;
import com.xatu.onlineedu.service.EduTeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-16
 */
@Controller
@CrossOrigin
@RequestMapping("/eduTeacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 根据条件查询讲师列表
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @PostMapping("/list/{current}/{limit}")
    @ResponseBody
    public Result list(@PathVariable long current, @PathVariable long limit,
                       @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(StringUtils.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(level!=null) {
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");
        pageTeacher = eduTeacherService.page(pageTeacher, queryWrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return Result.success().data("total",total).data("rows",records);

    }

    /**
     * 根据id删除讲师
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteDataById(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if(b){
             return Result.success();
         }else{
            return Result.fail();
        }
    }

    /**
     * 添加讲师
     *
     */
    @PostMapping("/addTeacher")
    @ResponseBody
    public Result addTeacher(@RequestBody EduTeacher teacher){
        boolean save = eduTeacherService.save(teacher);
        if(save){
            return Result.success();
        }else{
            return Result.fail();
        }
    }
    /**
     * 根据id查询讲师
     *
     */
    @GetMapping("/getTeacher/{id}")
    @ResponseBody
    public Result getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.success().data("teacher",eduTeacher);
    }
    /**
     * 根据id修改讲师信息
     */
    @PostMapping("/updateTeacher")
    @ResponseBody
    public Result updateTeacher(@RequestBody EduTeacher teacher){
        eduTeacherService.updateById(teacher);
        return Result.success();
    }
    /**
     * 获取所有讲师
     */
    @GetMapping("/getAllTeacher")
    @ResponseBody
    public Result getAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.success().data("teacherList",list);
    }


    /**
     * Index页面获取讲师数据
     * @return
     */
    @GetMapping("/getFrontTeacherList")
    @ResponseBody
    public Result getFrontTeacherList () {
        QueryWrapper<EduTeacher>  queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        queryWrapper.last("limit 4");
        List<EduTeacher> list = eduTeacherService.list(queryWrapper);
        return Result.success().data("teacherList",list);
    }


    @GetMapping("/getRemoteTeacher/{id}")
    @ResponseBody
    public EduTeacher getRemoteTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return eduTeacher;
    }



}