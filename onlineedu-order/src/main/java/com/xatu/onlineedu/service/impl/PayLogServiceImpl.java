package com.xatu.onlineedu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.xatu.onlineedu.entity.Order;
import com.xatu.onlineedu.entity.PayLog;
import com.xatu.onlineedu.mapper.PayLogMapper;
import com.xatu.onlineedu.service.OrderService;
import com.xatu.onlineedu.service.PayLogService;
import com.xatu.onlineedu.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-15
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    OrderService orderService;

    @Override
    public Map createNative(String orderNo)  {
        /**
         * 根据订单号获取订单信息
         */
        try{

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(queryWrapper);
        Map m = new HashMap();
        //设置支付参数
        m.put("appid","wx74862e0dfcf69954");
        m.put("mch_id","1558950191");
        m.put("nonce_str", WXPayUtil.generateNonceStr());
        m.put("body", order.getCourseTitle());
        m.put("out_trade_no", orderNo);
        m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
        m.put("spbill_create_ip", "127.0.0.1");
        m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
        m.put("trade_type", "NATIVE");

        //HTTPClient来根据URL访问第三方接口并且传递参数
        HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");

        //client设置参数
        client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
        client.setHttps(true);
        client.post();
        //3、返回第三方的数据
        String xml = client.getContent();
        Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
        //4、封装返回结果集
        Map map = new HashMap<>();
        map.put("out_trade_no", orderNo);
        map.put("course_id", order.getCourseId());
        map.put("total_fee", order.getTotalFee());
        map.put("result_code", resultMap.get("result_code"));
        map.put("code_url", resultMap.get("code_url"));
        return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
        //微信支付二维码2小时过期，可采取2小时未支付取消订单
        //redisTemplate.opsForValue().set(orderNo, map, 120, TimeUnit.MINUTES);
  }

    @Override
    public Map<String, String> queryStatus(String orderNo) {
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //6、转成Map
            //7、返回
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void updateOrderStatus(Map<String, String> map) {

            //获取订单id
            String orderNo = map.get("out_trade_no");
            //根据订单id查询订单信息
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            Order order = orderService.getOne(wrapper);

            if(order.getStatus().intValue() == 1) return;
            order.setStatus(1);
            orderService.updateById(order);

            //记录支付日志
            PayLog payLog=new PayLog();
            payLog.setOrderNo(order.getOrderNo());//支付订单号
            payLog.setPayTime(new Date());
            payLog.setPayType(1);//支付类型
            payLog.setTotalFee(order.getTotalFee());//总金额(分)
            payLog.setTradeState(map.get("trade_state"));//支付状态
            payLog.setTransactionId(map.get("transaction_id"));
            payLog.setAttr(JSONObject.toJSONString(map));
            baseMapper.insert(payLog);//插入到支付日志表
        }


}