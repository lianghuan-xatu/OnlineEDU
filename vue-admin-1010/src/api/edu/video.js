import request from '@/utils/request'

export default{
    removeVideoById(videoId) {
        return request({
            url: 'eduVideo/deleteVideoInfoById/'+videoId,
            method: 'delete'
        })
    },
    updateVideoById(videoInfo) {
        return request({
            url: 'eduVideo/updateVideoInfoById/'+videoInfo.id,
            method: 'put',
            data: videoInfo
        })
    },
    addVideoInfo(videoInfo) {
        return request({
            url: 'eduVideo/addVideoInfo/',
            method: 'post',
            data: videoInfo
        })
    },
    getVideoInfoById(videoId) {
        return request({
            url: 'eduVideo/getVideoInfoById/'+videoId,
            method: 'get'
        })
    }
}