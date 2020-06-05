package com.xatu.onlineedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xatu.onlineedu.entity.CrmBanner;
import com.xatu.onlineedu.entity.vo.BannerQueryVo;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.CrmBannerService;
import com.xatu.onlineedu.service.impl.CrmBannerServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 后台管理
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-02
 */
@RestController
@RequestMapping("/eduAdminCms")
@CrossOrigin
public class CrmBannerController {

    @Autowired
    CrmBannerService crmBannerService = new CrmBannerServiceImpl();



    @PostMapping("/getBannerList/{page}/{limit}")
    public Result getBannerList(@PathVariable long page,@PathVariable long limit,
                                @RequestBody BannerQueryVo crmBannerQuery) {
        Page<CrmBanner> pageBanner = new Page();
        pageBanner.setCurrent(page);
        pageBanner.setSize(limit);
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(crmBannerQuery.getTitle())){
            queryWrapper.like("title",crmBannerQuery.getTitle());
        }
        if(StringUtils.isNotEmpty(crmBannerQuery.getBegin())){
            queryWrapper.ge("gmt_create",crmBannerQuery.getBegin());
        }
        if(StringUtils.isNotEmpty(crmBannerQuery.getEnd())){
            queryWrapper.le("gmt_create",crmBannerQuery.getEnd());
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");

        crmBannerService.page(pageBanner,queryWrapper);
        List<CrmBanner> records = pageBanner.getRecords();
        long total = pageBanner.getTotal();
        return Result.success().data("total",total).data("list",records);
    }

    @PostMapping("/addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        boolean save = crmBannerService.save(crmBanner);
        if(save){
            return Result.success();
        }
        return Result.fail();
    }

    @GetMapping("/getBannerById/{id}")
    public Result getBannerById(@PathVariable String id) {
        CrmBanner crmBanner = crmBannerService.getById(id);
        return Result.success().data("banner",crmBanner);
    }

    @PutMapping("/updateBannerById/{id}")
    public Result updateBannerById(@PathVariable String id,
                                   @RequestBody CrmBanner crmBanner) {
        crmBanner.setId(id);
        boolean b = crmBannerService.updateById(crmBanner);
        if(b){
            return Result.success();
        }
        return Result.fail();
    }


    @DeleteMapping("/deleteBannerById/{id}")
    public Result deleteBannerById(@PathVariable String id) {
        boolean b = crmBannerService.removeById(id);
        if(b){
            return Result.success();
        }
        return Result.fail();
    }


}

