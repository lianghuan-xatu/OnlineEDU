
import request from '@/utils/request'
export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `/eduFrontCourse/pageList/`+page+'/'+limit,
      method: 'post',
      data: searchObj
    })
  },
  // 获取课程二级分类
  getNestedTreeList2() {
    return request({
      url: `/eduSubject/getAllSubject`,
      method: 'get'
    })
  },

  getById(courseId) {
    return request({
        url: `/eduFrontCourse/getById/`+courseId,
        method: 'get'
    })
}
}
