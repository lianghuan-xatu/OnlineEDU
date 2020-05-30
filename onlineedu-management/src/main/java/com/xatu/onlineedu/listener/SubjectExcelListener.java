package com.xatu.onlineedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xatu.onlineedu.entity.EduSubject;
import com.xatu.onlineedu.entity.excelEntity.SubjectData;
import com.xatu.onlineedu.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() { }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }
    //因为SubjectEcelListener不能交给spring管理，所以需要自己new，不能自动注入

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            try {
                throw new Exception("文件数据为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //一行一行读取，每次读取两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        EduSubject existOneSubject = existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if(existOneSubject == null){
            //添加到数据库
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(existOneSubject);
        }


        String parentId = existOneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), parentId);
        if(existTwoSubject == null){
            //添加到数据库中
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(parentId);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(existTwoSubject);
        }


    }
    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        EduSubject one = eduSubjectService.getOne(new QueryWrapper<EduSubject>().eq("title", name).eq("parent_id", "0"));
        return one;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String parentId){
        EduSubject one = eduSubjectService.getOne(new QueryWrapper<EduSubject>().eq("title", name).eq("parent_id", parentId));
        return one;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
