package com.xatu.onlineedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.entity.CrmBanner;
import com.xatu.onlineedu.mapper.CrmBannerMapper;
import com.xatu.onlineedu.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-02
 */
@Service
@Cacheable(value = "banner",key = "selectFrontBannerList")
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Autowired
    CrmBannerMapper crmBannerMapper;
    @Override
    public List<CrmBanner> getBannerList() {
        return crmBannerMapper.selectList(null);
    }
}
