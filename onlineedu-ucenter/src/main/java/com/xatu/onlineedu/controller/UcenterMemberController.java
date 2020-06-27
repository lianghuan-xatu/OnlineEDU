package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.entity.UcenterMember;
import com.xatu.onlineedu.entity.vo.RegisterVo;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.UcenterMemberService;
import com.xatu.onlineedu.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/eduUcenter")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    UcenterMemberService ucenterMemberService;

    //登录接口
    @PostMapping("/login")
    public Result loginUser(@RequestBody UcenterMember member, HttpServletRequest request){
        String token = ucenterMemberService.loginUser(member,request);
        return Result.success().data("token",token);
    }

    //注册接口
    @PostMapping("/register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.registerUser(registerVo);
        return Result.success();
    }

    //token验证
    @GetMapping("/getMemberInfoByToken")
    public Result getMemberInfoByToken(HttpServletRequest request) {
        //通过jwt校验token真假
        //通过jwt校验token真假
        UcenterMember ucenterMember;
        String token = request.getHeader("token");
        Map<String, Object> decode = null;
        if(StringUtils.isNotBlank(token)){
            decode = JwtUtil.decode(token, "onlineedu", "localhost");
        }
        if(decode!=null){
            String memberId = decode.get("memberId").toString();
            ucenterMember = ucenterMemberService.getById(memberId);
        }else{
            return Result.success().data("item","");
        }
        return Result.success().data("item",ucenterMember);
    }
    /**
     * 根据用户id获取用户信息
     */
    @GetMapping("/getMemberInfoByMemberId/{memberId}")
    public UcenterMember getMemberInfoByMemberId(@PathVariable String memberId) {
        UcenterMember ucenterMember = ucenterMemberService.getById(memberId);
        return ucenterMember;
    }

    /**
     * 统计每天注册人数
     */
    @GetMapping(value = "/registerCount/{day}")
    public Result registerCount(@PathVariable String day){
        Integer count = ucenterMemberService.countRegisterByDay(day);
        return Result.success().data("countRegister",count);
    }


}

