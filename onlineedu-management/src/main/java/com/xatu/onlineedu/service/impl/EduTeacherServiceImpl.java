package com.xatu.onlineedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.entity.EduTeacher;
import com.xatu.onlineedu.mapper.EduTeacherMapper;
import com.xatu.onlineedu.service.EduTeacherService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-18
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String, Object> pageList(Page<EduTeacher> pageParam) {
            QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("sort");
            baseMapper.selectPage(pageParam, queryWrapper);
            List<EduTeacher> records = pageParam.getRecords();
            long current = pageParam.getCurrent();
            long pages = pageParam.getPages();
            long size = pageParam.getSize();
            long total = pageParam.getTotal();
            boolean hasNext = pageParam.hasNext();
            boolean hasPrevious = pageParam.hasPrevious();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("items", records);
            map.put("current", current);
            map.put("pages", pages);
            map.put("size", size);
            map.put("total", total);
            map.put("hasNext", hasNext);
            map.put("hasPrevious", hasPrevious);
            return map;

    }
}
