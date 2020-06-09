import request from '@/utils/request'


export default{
    getStudentListPage(current,limit,studentQuery){
    return request({
        url: '/eduStudent/list/'+current+'/'+limit,
        //url: '/eduTeacher/list/${current}/${limit}',
        method: 'post',
        //teacherQuery条件对象，后端使用RequestBody获取数据  data表示把对象转换成json进行传输
        data: studentQuery
    })
},
//删除讲师 
deleteDataById(id) {
    return request({
        url: '/eduStudent/'+id,
        method: 'delete'
    })
},
 //添加讲师 
 addStudent(student) {
     return request({
         url: '/eduStudent/addStudent/',
         method: 'post',
         data: student

     })
 },
 //根据id获取讲师信息  
 getStudent(id) {
     return request({
         url: '/eduStudent/getStudent/'+id,
         method: 'get',
    
     })
 },
 //根据id更新讲师信息  
 updateStudent(student) {
     return request({
         url: '/eduStudent/updateStudent',
         method: 'post',
         data: student
     })
 },
 getAllStudent() {
     return request({
         url: '/eduStudent/getAllStudent',
         method: 'get'
     })
 }
}