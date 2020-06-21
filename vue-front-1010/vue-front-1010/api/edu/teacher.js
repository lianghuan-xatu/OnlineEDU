import request from '@/utils/request'
export default {
  getPageList(page, limit) {   
    return request({
      url: '/eduFrontTeacher/pageList/'+page+'/'+limit,
      method: 'get'
    })
  },
  getById(teacherId) {
    return request({
        url: '/eduFrontTeacher/getById/'+teacherId,
        method: 'get'
    })
}
}