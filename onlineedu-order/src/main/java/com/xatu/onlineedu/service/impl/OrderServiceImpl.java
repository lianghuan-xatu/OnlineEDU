package com.xatu.onlineedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.client.ManagementClient;
import com.xatu.onlineedu.client.UcenterClient;
import com.xatu.onlineedu.entity.EduTeacher;
import com.xatu.onlineedu.entity.Order;
import com.xatu.onlineedu.entity.UcenterMember;
import com.xatu.onlineedu.entity.vo.CourseVo;
import com.xatu.onlineedu.exception.EduException;
import com.xatu.onlineedu.mapper.OrderMapper;
import com.xatu.onlineedu.service.OrderService;
import com.xatu.onlineedu.util.JwtUtil;
import com.xatu.onlineedu.utils.OrderNoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    UcenterClient ucenterClient;
    @Autowired
    ManagementClient managementClient;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public String saveOrder(String courseId, HttpServletRequest request) {
        //远程调用获取用户信息
        String token = request.getHeader("token");
        Map<String, Object> decode = null;
        if(StringUtils.isNotBlank(token)){
            decode = JwtUtil.decode(token, "onlineedu", "localhost");
        }
        if(decode == null){
            throw new EduException(2001,"请登录");
        }
        String memberId = decode.get("memberId").toString();
        UcenterMember member = ucenterClient.getMemberInfoByMemberId(memberId);
        //远程调用获取课程信息
        CourseVo course = managementClient.getRemoteCourseInfoByCourseId(courseId);
        //根据课程信息和用户信息来创建新的订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(course.getId());
        order.setCourseTitle(course.getTitle());
        order.setCourseCover(course.getCover());
        EduTeacher teacher = managementClient.getRemoteTeacher(course.getTeacherId());
        order.setTeacherName(teacher.getName());
        order.setMemberId(member.getId());
        order.setNickname(member.getNickname());
        order.setMobile(member.getMobile());
        order.setTotalFee(course.getPrice());
        order.setStatus(0);
        orderMapper.insert(order);
        return order.getOrderNo();
    }

}
