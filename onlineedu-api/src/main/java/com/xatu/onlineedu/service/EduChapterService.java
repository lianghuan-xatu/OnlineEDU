package com.xatu.onlineedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xatu.onlineedu.entity.EduChapter;
import com.xatu.onlineedu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-16
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getNestedListByCourseId(String courseId);

    boolean removeChapterById(String chapterId);
}
