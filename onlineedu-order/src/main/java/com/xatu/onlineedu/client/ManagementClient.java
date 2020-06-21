package com.xatu.onlineedu.client;

import com.xatu.onlineedu.entity.EduTeacher;
import com.xatu.onlineedu.entity.vo.CourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "edu-management")
public interface ManagementClient {


    @GetMapping("/eduCourse/getRemoteCourseInfoByCourseId/{courseId}")
    public CourseVo getRemoteCourseInfoByCourseId(@PathVariable String courseId);


    /**
     * 根据id查询讲师
     *
     */
    @GetMapping("/eduTeacher/getRemoteTeacher/{id}")
    public EduTeacher getRemoteTeacher(@PathVariable String id);


}
