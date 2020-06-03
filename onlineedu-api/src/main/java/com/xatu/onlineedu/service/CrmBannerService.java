package com.xatu.onlineedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xatu.onlineedu.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-02
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getBannerList();
}
