package com.xatu.onlineedu.controller;


import com.alibaba.fastjson.JSON;
import com.xatu.onlineedu.entity.UcenterMember;
import com.xatu.onlineedu.entity.vo.RegisterVo;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.UcenterMemberService;
import com.xatu.onlineedu.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    public String getMemberInfoByToken(String token,String currentIp) {
        //通过jwt校验token真假
        //通过jwt校验token真假
        Map<String,String> map = new HashMap<>();
        Map<String, Object> decode = null;
        if(StringUtils.isNotBlank(token)&&StringUtils.isNotBlank(currentIp)){
            decode = JwtUtil.decode(token, "onlineedu", currentIp);
        }
        if(decode!=null){
            map.put("status","success");
            map.put("memberId",decode.get("memberId").toString());
            map.put("nickName",decode.get("nickName").toString());
        }else{
            map.put("status","fail");
        }
        return JSON.toJSONString(map);
    }

}

