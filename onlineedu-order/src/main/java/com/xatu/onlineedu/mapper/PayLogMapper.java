package com.xatu.onlineedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xatu.onlineedu.entity.PayLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-15
 */
@Mapper
@Repository
public interface PayLogMapper extends BaseMapper<PayLog> {

}
