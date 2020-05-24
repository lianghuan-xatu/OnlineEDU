package com.xatu.onlineedu.controller;

import com.xatu.onlineedu.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class EduLoginController {

    @PostMapping("/login")
    public Result login(){
        return Result.success().data("token","admin");
    }

    @GetMapping("/info")
    public Result info(){
        return Result.success().data("roles","[admin]");
    }




}
