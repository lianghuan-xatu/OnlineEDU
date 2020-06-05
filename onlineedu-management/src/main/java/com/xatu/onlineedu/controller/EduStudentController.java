package com.xatu.onlineedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xatu.onlineedu.entity.EduStudent;
import com.xatu.onlineedu.entity.vo.StudentQuery;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.EduStudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 学生 前端控制器
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/eduStudent")
@CrossOrigin
public class EduStudentController {

    @Autowired
    private EduStudentService eduStudentService;

    /**
     * 根据条件查询讲师
     * @param current
     * @param limit
     * @param
     * @return
     */
    @PostMapping("/list/{current}/{limit}")
    @ResponseBody
    public Result list(@PathVariable long current, @PathVariable long limit,
                       @RequestBody(required = false) StudentQuery studentQuery){

        Page<EduStudent> pageStudent = new Page<>(current,limit);
        QueryWrapper<EduStudent> queryWrapper = new QueryWrapper<>();
        String name = studentQuery.getName();
        String sex = studentQuery.getSex();
        String className = studentQuery.getClassName();
        String begin = studentQuery.getBegin();
        String end = studentQuery.getEnd();

        if(StringUtils.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(StringUtils.isNotBlank(sex)){
            queryWrapper.eq("sex",sex);
        }
        if(StringUtils.isNotBlank(className)){
            queryWrapper.eq("class_name",className);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");
        pageStudent = eduStudentService.page(pageStudent, queryWrapper);
        long total = pageStudent.getTotal();
        List<EduStudent> records = pageStudent.getRecords();
        return Result.success().data("total",total).data("rows",records);

    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteDataById(@PathVariable String id){
        boolean b = eduStudentService.removeById(id);
        if(b){
            return Result.success();
        }else{
            return Result.fail();
        }
    }

    /**
     * 添加
     *
     */
    @PostMapping("/addStudent")
    @ResponseBody
    public Result addStudent(@RequestBody EduStudent student){
        boolean save = eduStudentService.save(student);
        if(save){
            return Result.success();
        }else{
            return Result.fail();
        }
    }
    /**
     * 根据id查询
     *
     */
    @GetMapping("/getStudent/{id}")
    @ResponseBody
    public Result getStudent(@PathVariable String id){
        EduStudent eduStudent = eduStudentService.getById(id);
        return Result.success().data("student",eduStudent);
    }
    /**
     * 根据id修改信息
     */
    @PostMapping("/updateStudent")
    @ResponseBody
    public Result updateStudent(@RequestBody EduStudent eduStudent){
        eduStudentService.updateById(eduStudent);
        return Result.success();
    }
    /**
     * 获取所有
     */
    @GetMapping("/getAllStudent")
    @ResponseBody
    public Result getAllStudent(){
        List<EduStudent> list = eduStudentService.list(null);
        return Result.success().data("studentList",list);
    }




}

