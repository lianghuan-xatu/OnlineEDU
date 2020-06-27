import request from '@/utils/request'

export default {

  createStatistics(day) {
    return request({
      url: 'eduStatistics/'+day,
      method: 'post'
    })
  },
  showChart(searchObj) {
    return request({
        url: 'eduStatistics/show-chart/'+searchObj.begin+'/'+searchObj.end+'/'+searchObj.type,
        method: 'get'
    })
}
}
