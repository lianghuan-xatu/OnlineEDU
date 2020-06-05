<template>
  <div class="app-container">
      添加学生   
     
    <el-form label-width="120px">
      <el-form-item label="学生名称">
        <el-input v-model="student.name"/>
      </el-form-item>
      <el-form-item label="学生排序">
        <el-input-number v-model="student.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="学生性别">
        <el-select v-model="student.sex" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="'男'" label="男"/>
          <el-option :value="'女'" label="女"/>
        </el-select>
      </el-form-item>
      <el-form-item label="学生班级">
        <el-input v-model="student.className"/>
      </el-form-item>
       <el-form-item label="学生年龄">
           <el-input-number :min="0" v-model="student.age" controls-position="right" placeholder="请填写学生年龄"/>
      </el-form-item>
       <el-form-item label="语文成绩">
        <el-input v-model="student.chScore"/>
      </el-form-item>
       <el-form-item label="数学成绩">
        <el-input v-model="student.mathScore"/>
      </el-form-item>
      <el-form-item label="英语成绩">
        <el-input v-model="student.engScore" />
      </el-form-item>

       <el-form-item label="程序设计成绩">
        <el-input v-model="student.javaScore" />
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import studentApi from '@/api/edu/student'
export default {
  components: {},
  data() {
    return {
      student:{
        id: '',
        name: '',
        sort: 0,
        age: 0,
        sex: '',
        className: '',
        chScore: '',
        mathScore: '',
        engScore: '',
        javaScore: ''
      },
      saveBtnDisabled: false //保存按钮是否禁用


    }
  },
  created() {
//页面渲染之前调用 
this.init()

},
  watch: {
    //监听 
    $route(to,from) {
      //路由变化方法就会执行   
      this.init()

    }

  },
   methods: {

    init() { 
    //判断路径是否有id值
    if(this.$route.params && this.$route.params.id) {
    
     //从路径中获取id值
     const id = this.$route.params.id
      this.getInfo(id)
    }else{
      this.student= {}
    }
    },

    saveOrUpdate() {
      //判断是修改还是添加  根据teacher中是否有id判断   
      if(!this.student.id){
         this.saveStudent()
      }else{
        this.updateStudent()
      }
     

    },
    //添加讲师
    saveStudent() {
      studentApi.addStudent(this.student)
      .then(response =>{//添加成功  
        //提示成功
          this.$message({
                    type: 'success',
                    message: '添加成功!'
                      });
        //回到列表,路由跳转  
        this.$router.push({path: '/student/table'})
      })
      

    },

    //根据id查询讲师 
    getInfo(id) {
      studentApi.getStudent(id)
      .then(response =>{
        this.student = response.data.student
      })
    },
    //修改讲师方法  
    updateStudent() {
      studentApi.updateStudent(this.student)
      .then(response =>{
        //提示成功
          this.$message({
                    type: 'success',
                    message: '修改成功!'
                      });

        //回到页面  
        this.$router.push({path:'/student/table'})
      })
    }
 

  }
}
</script>