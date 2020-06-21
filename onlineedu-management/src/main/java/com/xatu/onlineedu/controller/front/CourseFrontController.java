package com.xatu.onlineedu.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xatu.onlineedu.client.OrderClient;
import com.xatu.onlineedu.entity.EduCourse;
import com.xatu.onlineedu.entity.vo.ChapterVo;
import com.xatu.onlineedu.entity.vo.CourseFrontQueryVo;
import com.xatu.onlineedu.entity.vo.CourseWebVo;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.EduChapterService;
import com.xatu.onlineedu.service.EduCourseService;
import com.xatu.onlineedu.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduFrontCourse")
public class CourseFrontController {

    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;

    @PostMapping(value = "/pageList/{page}/{limit}")
    public Result pageList(
            @PathVariable Long page,
            @PathVariable Long limit,
            @RequestBody(required = false) CourseFrontQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = eduCourseService.pageListWeb(pageParam, courseQuery);
        return Result.success().data("resultMap",map);
    }


    @GetMapping(value = "/getById/{courseId}")
    public Result getById(
            @PathVariable String courseId, HttpServletRequest httpServletRequest){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = eduCourseService.selectInfoWebById(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.getNestedListByCourseId(courseId);
        //远程调用，判断课程是否被购买
        String token = httpServletRequest.getHeader("token");
        Map<String, Object> decode = null;
        if(StringUtils.isNotBlank(token)){
            decode = JwtUtil.decode(token, "onlineedu", "localhost");
        }
        String memberId  = "";
        if(decode!=null){
            memberId = decode.get("memberId").toString();
        }
        boolean buyCourse = orderClient.isBuyCourse(memberId, courseId);


        return Result.success().data("course", courseWebVo).data("chapterVoList", chapterVoList).data("isbuy",buyCourse);
    }






}
