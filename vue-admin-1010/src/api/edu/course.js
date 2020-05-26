import request from '@/utils/request'
export default {
    addCourseInfo(courseInfo) {
        return request({
            url: 'eduCourse/saveCourseInfo',
            method: 'post',
            data: courseInfo
        })
    },
    updateCourseInfo(courseInfo){
        return request({
            url: 'eduCourse/updateCourseInfo',
            method: 'post',
            data: courseInfo
        })
    }
    ,
    fetchCourseInfo(courseId) {
        return request({
            url:'eduCourse/getCourseInfoByCourseId/'+courseId,
            method: 'get'
        })
    }
}