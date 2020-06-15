package com.xatu.onlineedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xatu.onlineedu.client.UcenterClient;
import com.xatu.onlineedu.entity.EduComment;
import com.xatu.onlineedu.entity.UcenterMember;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.EduCommentService;
import com.xatu.onlineedu.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduComment")
public class CommentFrontController {
    @Autowired
    private EduCommentService eduCommentService;
    @Autowired
    private UcenterClient ucenterClient;

        //根据课程id查询评论列表
        @GetMapping("/index/{page}/{limit}")
        public Result index(

                @PathVariable Long page,
                @PathVariable Long limit,
                        String courseId) {
            Page<EduComment> pageParam = new Page<>(page, limit);

            QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
            wrapper.eq("course_id",courseId);

            eduCommentService.page(pageParam,wrapper);
            List<EduComment> commentList = pageParam.getRecords();

            Map<String, Object> map = new HashMap<>();
            map.put("items", commentList);
            map.put("current", pageParam.getCurrent());
            map.put("pages", pageParam.getPages());
            map.put("size", pageParam.getSize());
            map.put("total", pageParam.getTotal());
            map.put("hasNext", pageParam.hasNext());
            map.put("hasPrevious", pageParam.hasPrevious());
            return Result.success().data("resultMap",map);
        }

    /**
     * 添加评论
     * @param comment
     * @param request
     * @return
     */
        @PostMapping("/save")
        public Result save(@RequestBody EduComment comment, HttpServletRequest request) {
            UcenterMember ucenterMember = null;
            String token = request.getHeader("token");
            Map<String, Object> decode = null;
            if(StringUtils.isNotBlank(token)){
                decode = JwtUtil.decode(token, "onlineedu", "localhost");
            }
            if(decode!=null){
                String memberId = decode.get("memberId").toString();
                 ucenterMember = ucenterClient.getMemberInfoByMemberId(memberId);
            }

            if(StringUtils.isEmpty(ucenterMember.getId())) {
                return Result.fail().code(28004).message("请登录");
            }
            comment.setMemberId(ucenterMember.getId());
            comment.setNickname(ucenterMember.getNickname());
            comment.setAvatar(ucenterMember.getAvatar());

            eduCommentService.save(comment);
            return Result.success();
        }



}
