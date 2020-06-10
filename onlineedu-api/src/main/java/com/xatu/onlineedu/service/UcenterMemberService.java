package com.xatu.onlineedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xatu.onlineedu.entity.UcenterMember;
import com.xatu.onlineedu.entity.vo.RegisterVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-09
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String loginUser(UcenterMember member, HttpServletRequest request);

    void registerUser(RegisterVo registerVo);

    boolean isExistMobile(String mobile);

    UcenterMember getByOpenid(String openid);
}
