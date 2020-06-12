package com.xatu.onlineedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xatu.onlineedu.entity.EduTeacher;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-16
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> pageList(Page<EduTeacher> teacherPage);
}
