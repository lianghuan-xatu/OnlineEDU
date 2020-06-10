package com.xatu.onlineedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.entity.UcenterMember;
import com.xatu.onlineedu.entity.vo.RegisterVo;
import com.xatu.onlineedu.exception.EduException;
import com.xatu.onlineedu.mapper.UcenterMemberMapper;
import com.xatu.onlineedu.service.UcenterMemberService;
import com.xatu.onlineedu.util.JwtUtil;
import com.xatu.onlineedu.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-09
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    UcenterMemberMapper ucenterMemberMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UcenterMemberService ucenterMemberService;

    @Override
    public String loginUser(UcenterMember member, HttpServletRequest request) {
        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();
        //判断手机号和密码非空
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw  new EduException(2001,"登陆失败！");
        }
        UcenterMember ucenterMember = ucenterMemberMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(ucenterMember==null){
            //没有这个手机号的用户
            throw new EduException(2001,"此用户不存在");
        }
        //判断密码
        password = MD5.encrypt(password);
        if(!password.equals(ucenterMember.getPassword())){
            //密码错误
            throw new EduException(2001,"密码错误");
        }
        //判断用户是否激活状态
        if(ucenterMember.getDisabled() == true){
            //用户未激活
            throw new EduException(2001,"无此用户");
        }

        //登陆成功，生成token
        String memberId = ucenterMember.getId();
        String nickName = ucenterMember.getNickname();
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("memberId",memberId);
        userMap.put("nickName",nickName);
        String ip ="localhost";
        /*String ip = request.getHeader("x-forwarded-for");//通过nginx转发的获得的客户端ip
        if(StringUtils.isBlank(ip)){
            ip = request.getRemoteAddr();//从request中的ip
            if(StringUtils.isBlank(ip)){
                ip = "localhost";
            }
        }*/
        //按照设计的算法对参数进行加密，生成token
        String token = JwtUtil.encode("onlineedu",userMap,ip);
        return token;
    }

    @Override
    public void registerUser(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        if(StringUtils.isEmpty(nickname)|| StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)|| StringUtils.isEmpty(code)) {
            throw new EduException(2001,"注册失败");
        }
        //判断验证码是否过期和正确
        String redisCode =  redisTemplate.opsForValue().get(mobile).toString();
        if(!code.equals(redisCode)){
            throw new EduException(2001,"注册失败");
        }
        //验证手机号码是否重复
        boolean existMobile = ucenterMemberService.isExistMobile(mobile);
        if(existMobile){
            throw new EduException(2001,"手机号码已注册");
        }
        //数据库中添加新用户
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickname);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setDisabled(false);//用户不禁用
        ucenterMember.setAvatar("https://onlineedu-100.oss-cn-beijing.aliyuncs.com/2020/06/05/2bad8237-3bce-4d02-84f4-1ed8e8c8f91dfile.png");//默认头像
        int insert = ucenterMemberMapper.insert(ucenterMember);
        if(insert<=0){
            throw new EduException(2001,"注册失败");
        }
    }

    @Override
    public boolean isExistMobile(String mobile) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = ucenterMemberMapper.selectOne(queryWrapper);
        if(ucenterMember!=null){
            return true;
        }
        return false;
    }

    @Override
    public UcenterMember getByOpenid(String openid) {
        return ucenterMemberMapper.selectOne(new QueryWrapper<UcenterMember>().eq("openid",openid));
    }
}
