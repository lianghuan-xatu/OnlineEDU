package com.xatu.onlineedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xatu.onlineedu.entity.EduSubject;
import com.xatu.onlineedu.entity.excelEntity.SubjectData;
import com.xatu.onlineedu.entity.subject.OneSubject;
import com.xatu.onlineedu.entity.subject.TwoSubject;
import com.xatu.onlineedu.listener.SubjectExcelListener;
import com.xatu.onlineedu.mapper.EduSubjectMapper;
import com.xatu.onlineedu.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author LiangHuan
 * @since 2020-05-18
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    EduSubjectMapper eduSubjectMapper;

    /**
     * 添加课程分类
     * @param file
     */
    @Override
    public void saveFile(MultipartFile file,EduSubjectService eduSubjectService) {
        try{
            //文件流
            InputStream inputStream = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //查询出所有一级分类
        List<EduSubject> OneSubjectList = eduSubjectMapper.selectList(new QueryWrapper<EduSubject>().eq("parent_id", 0));
        //查询出所有的二级分类
        List<EduSubject> TwoSubjectList = eduSubjectMapper.selectList(new QueryWrapper<EduSubject>().ne("parent_id", 0));
        List<OneSubject> subjects = new ArrayList<>();//存放最终结果
        //遍历一级分类
        for (EduSubject eduSubject : OneSubjectList) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            String id = oneSubject.getId();
            for (EduSubject subject : TwoSubjectList) {
                if(id.equals(subject.getParentId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(subject,twoSubject);
                    oneSubject.getTwoSubjectList().add(twoSubject);
                }
            }
            subjects.add(oneSubject);
        }
        return subjects;
    }
}
