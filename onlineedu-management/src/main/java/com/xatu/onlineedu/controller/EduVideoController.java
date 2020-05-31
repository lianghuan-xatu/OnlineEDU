package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.entity.EduVideo;
import com.xatu.onlineedu.exception.EduException;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-16
 */
@RestController
@RequestMapping("/eduVideo")
@CrossOrigin
public class EduVideoController {


    @Autowired
    EduVideoService eduVideoService;


    @PostMapping("/addVideoInfo")
    public Result addVideoInfo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        if(save){
            return Result.success();
        }else{
            return Result.fail();
        }
    }

    @DeleteMapping("/deleteVideoInfoById/{videoId}")
    public Result deleteVideoInfoById(@PathVariable String videoId){
        Boolean success = eduVideoService.deleteVideoInfoById(videoId);
        if(success){
            return Result.success();
        }
        return Result.fail();
    }


    @PutMapping("/updateVideoInfoById/{videoId}")
    public Result updateVideoInfoById(@PathVariable String videoId,
                                      @RequestBody EduVideo eduVideo){
        eduVideo.setId(videoId);
        boolean b = eduVideoService.updateById(eduVideo);
        if(!b){
            throw new EduException(2001,"课时信息更新失败");
        }
        return Result.success();

    }


    @GetMapping("/getVideoInfoById/{videoId}")
    public Result getVideoInfoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return Result.success().data("item",eduVideo);
    }

}

