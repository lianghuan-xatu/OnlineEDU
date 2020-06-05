package com.xatu.onlineedu.mapper;

import com.xatu.onlineedu.entity.CrmBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 首页banner表 Mapper 接口
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-02
 */
@Mapper
@Repository
public interface CrmBannerMapper extends BaseMapper<CrmBanner> {

}
