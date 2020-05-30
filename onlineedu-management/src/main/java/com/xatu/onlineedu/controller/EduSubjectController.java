package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.entity.subject.OneSubject;
import com.xatu.onlineedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-16
 */
@RestController
@RequestMapping("/eduSubject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    //获取上传过来的文件，把文件呢绒获取过来
    @PostMapping("/addSubject")
    public Result addSubject(MultipartFile file){
        //上传过来excel文件
        eduSubjectService.saveFile(file,eduSubjectService);
        return Result.success();
    }




    @GetMapping("/getAllSubject")
    public Result getAllSubject(){
        List<OneSubject> list =eduSubjectService.getAllOneTwoSubject();
        return Result.success().data("list",list);
    }


}

