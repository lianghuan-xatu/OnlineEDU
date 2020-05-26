import request from '@/utils/request'
export default {
    getNestedTreeList(courseId) {
        return request({
            url: 'eduChapter/getNestedList/'+courseId,
            method: 'get'
        })
    }
}