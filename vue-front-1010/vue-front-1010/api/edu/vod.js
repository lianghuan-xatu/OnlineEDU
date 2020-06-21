import request from '@/utils/request'

export default {

  getPlayAuth(vid) {
    return request({
      url: `eduVod/get-play-auth/`+vid,
      method: 'get'
    })
  }

}