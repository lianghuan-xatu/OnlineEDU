package com.xatu.onlineedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.entity.EduVideo;
import com.xatu.onlineedu.mapper.EduVideoMapper;
import com.xatu.onlineedu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-18
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    EduVideoMapper eduVideoMapper;

    @Override
    public boolean getCountByChapterId(String chapterId) {
        Integer count = eduVideoMapper.selectCount(new QueryWrapper<EduVideo>().eq("chapter_id", chapterId));
        return null!=count&&count>0;
    }
}
