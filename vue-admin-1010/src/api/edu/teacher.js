import request from '@/utils/request'


export default{
    getTeacherListPage(current,limit,teacherQuery){
    return request({
        url: '/eduTeacher/list/'+current+'/'+limit,
        //url: '/eduTeacher/list/${current}/${limit}',
        method: 'post',
        //teacherQuery条件对象，后端使用RequestBody获取数据  data表示把对象转换成json进行传输
        data: teacherQuery
    })
},
//删除讲师 
deleteDataById(id) {
    return request({
        url: '/eduTeacher/'+id,
        method: 'delete'
    })
},
 //添加讲师 
 addTeacher(teacher) {
     return request({
         url: '/eduTeacher/addTeacher/',
         method: 'post',
         data: teacher

     })
 },
 //根据id获取讲师信息  
 getTeacherInfo(id) {
     return request({
         url: '/eduTeacher/getTeacher/'+id,
         method: 'get',
    
     })
 },
 //根据id更新讲师信息  
 updateTeacher(teacher) {
     return request({
         url: '/eduTeacher/updateTeacher',
         method: 'post',
         data: teacher
     })
 },
 getAllTeacher() {
     return request({
         url: '/eduTeacher/getAllTeacher',
         method: 'get'
     })
 }
}