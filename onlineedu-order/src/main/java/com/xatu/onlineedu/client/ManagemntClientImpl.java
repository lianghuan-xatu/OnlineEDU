package com.xatu.onlineedu.client;

import com.xatu.onlineedu.entity.EduTeacher;
import com.xatu.onlineedu.entity.vo.CourseVo;
import com.xatu.onlineedu.exception.EduException;
import org.springframework.stereotype.Component;


@Component
public class ManagemntClientImpl implements ManagementClient{

    @Override
    public CourseVo getRemoteCourseInfoByCourseId(String courseId) {
        return null;
    }

    @Override
    public EduTeacher getRemoteTeacher(String id) {
        throw new EduException(2001,"timeout");
    }


}
