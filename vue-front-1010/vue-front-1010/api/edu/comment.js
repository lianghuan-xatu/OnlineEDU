import request from '@/utils/request'

export default {

  getPageList(page, limit, courseId) {
    return request({
      url: `/eduComment/index/${page}/${limit}`,
      method: 'get',
      params: {courseId}
    })
  },
  addComment(comment) {
    return request({
      url: `/eduComment/save`,
      method: 'post',
      data: comment
    })
  }
}