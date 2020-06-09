import request from '@/utils/request'
export default {

    getBannerList() {
        return request({
            url: '/eduFrontCms/getFrontBanner/',
            method: 'get'
        })
    },
    getTeacherList() {
        return request({
            url: '/eduTeacher/getFrontTeacherList/',
            method: 'get'
        })
    },
    getCourseList() {
        return request({
            url: '/eduCourse/getFrontCourseList/',
            method: 'get'
        })
    }



}