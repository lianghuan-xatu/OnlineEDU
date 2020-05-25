package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.entity.vo.ChapterVo;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.EduChapterService;
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
@RequestMapping("/eduChapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService eduChapterService;

    @GetMapping("/getNestedList/{courseId}")
    public Result getNestedListByCourseId(@PathVariable String courseId){
        List<ChapterVo> chapterList = eduChapterService.getNestedListByCourseId(courseId);
        return Result.success().data("items",chapterList);
    };

}

