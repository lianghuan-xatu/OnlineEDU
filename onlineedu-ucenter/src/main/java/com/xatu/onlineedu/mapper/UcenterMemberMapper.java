package com.xatu.onlineedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xatu.onlineedu.entity.UcenterMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-09
 */
@Mapper
@Repository
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
}
