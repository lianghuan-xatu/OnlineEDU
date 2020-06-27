package com.xatu.onlineedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xatu.onlineedu.entity.StatisticsDaily;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-22
 */
@Mapper
@Repository
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {

}
