package com.xatu.onlineedu.mapper;

import com.xatu.onlineedu.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-15
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
