package com.xatu.onlineedu.controller;


import com.xatu.onlineedu.entity.CrmBanner;
import com.xatu.onlineedu.result.Result;
import com.xatu.onlineedu.service.CrmBannerService;
import com.xatu.onlineedu.service.impl.CrmBannerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduFrontCms")
public class CrmBannerFrontController {

    @Autowired
    CrmBannerService crmBannerService = new CrmBannerServiceImpl();

    @GetMapping("/getBannerList")
    public Result getBannerList() {
        List<CrmBanner> crmBanners = crmBannerService.getBannerList();
        return Result.success().data("list",crmBanners);
    }


}
