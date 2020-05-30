import request from '@/utils/request'
export default {
    getNestedTreeList(courseId) {
        return request({
            url: 'eduChapter/getNestedList/'+courseId,
            method: 'get'
        })
    },
    removeChapterById(chapterId) {
        return request({
            url: 'eduChapter/removeChapterById/'+chapterId,
            method: 'delete'
        })
    },
    updateChapterById(chapter) {
        return request({
            url: 'eduChapter/updateById/'+chapter.id,
            method: 'put',
            data: chapter
        })
    },
    addChapter(chapter) {
        return request({
            url: 'eduChapter/addChapter/',
            method: 'post',
            data: chapter
        })
    },
    getChapterById(chapterId) {
        return request({
            url: 'eduChapter/getChapterById/'+chapterId,
            method: 'get'
        })
    },
    removeVideo(videoId) {
        return request({
            url: 'eduVod/removeVideo/'+videoId,
            method: 'delete'
        })
    }

}