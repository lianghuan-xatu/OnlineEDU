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
    },
    getPublishCourseInfo(courseId){
        return request({
            url:'eduCourse/getPublishCourseInfo/'+courseId,
            method: 'get'
        })
    },
    publishCourse(courseId) {
        return request({
            url:'eduCourse/publishCourse/'+courseId,
            method: 'post'
        })
    },
    getCourseListPage(page,limit,courseQuery) {
        return request({
            url:'eduCourse/getCourseListPage/'+page+'/'+limit+"/",
            method: 'post',
            data: courseQuery
        })
    }
    ,
    deleteDataById(id) {
        return request({
            url:'eduCourse/deleteDataById/'+id,
            method: 'delete'
        })
    }

}