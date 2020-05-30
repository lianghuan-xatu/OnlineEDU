package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.entity.EduChapter;
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


    @DeleteMapping("/removeChapterById/{chapterId}")
    public Result removeChapterById(@PathVariable String chapterId){
        boolean success = eduChapterService.removeChapterById(chapterId);
        if(success == true){
            return Result.success();
        }else{
            return Result.fail();
        }
    }


    @PutMapping("/updateById/{chapterId}")
    public Result updateById(@PathVariable String chapterId,
                             @RequestBody EduChapter chapter){

        chapter.setId(chapterId);
        boolean b = eduChapterService.updateById(chapter);
        if(b){
            return Result.success();
        }
            return Result.fail();
    }

    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        if(save){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @GetMapping("/getChapterById/{chapterId}")
    public Result getChapterById(@PathVariable String chapterId){
        EduChapter chapter = eduChapterService.getById(chapterId);
        return Result.success().data("chapter",chapter);
    }





}

