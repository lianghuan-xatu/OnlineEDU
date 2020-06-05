import request from '@/utils/request'
export default {

    getBannerListPage(page,limit,bannerQuery) {
        return request({
            url:'eduAdminCms/getBannerList/'+page+'/'+limit+"/",
            method: 'post',
            data: bannerQuery
        })
    }
    ,
    deleteDataById(id) {
        return request({
            url:'eduAdminCms/deleteBannerById/'+id,
            method: 'delete'
        })
    },
    getBannerInfoByBannerId(bannerId) {
        return request({
            url:'eduAdminCms/getBannerById/'+bannerId,
            method: 'get'
        })

    },
    addBannerInfo(bannerInfo) {
        return request({
            url:'eduAdminCms/addBanner/',
            method: 'post',
            data: bannerInfo
        })

    },
    updateBannerInfo(bannerInfo) {
        return request({
            url:'eduAdminCms/updateBannerById/'+bannerInfo.id,
            method: 'put',
            data: bannerInfo
        })
    }
}