package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-22
 */
@RestController
@RequestMapping("/eduStatistics")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    StatisticsDailyService statisticsDailyService;

    @PostMapping("/{day}")
    public Result createStatisticsByDate(@PathVariable String day) {
        statisticsDailyService.createStatisticsByDay(day);
        return Result.success();
    }

    @GetMapping("show-chart/{begin}/{end}/{type}")
    public Result showChart(@PathVariable String begin, @PathVariable String end, @PathVariable String type){
        Map<String, Object> map = statisticsDailyService.getChartData(begin, end, type);
        return Result.success().data("resultMap",map);
    }

}

