<template>
  <div class="app-container">
    学生列表
      <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="studentQuery.name" placeholder="学生名"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="studentQuery.sex" clearable placeholder="学生性别">
          <el-option :value="'男'" label="男"/>
          <el-option :value="'女'" label="女"/>
        </el-select>
      </el-form-item>

       <el-form-item>
        <el-input v-model="studentQuery.className" placeholder="学生班级"/>
      </el-form-item>


       <el-form-item>
        <el-input v-model="studentQuery.age" placeholder="学生年龄"/>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="studentQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="studentQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    
          <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row>

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="姓名" width="100" />

      <el-table-column prop="sex" label="性别" width="100" />
      
       <el-table-column prop="className" label="班级" width="100" />

       <el-table-column prop="age" label="年龄" width="100" />

       <el-table-column prop="chScore" label="语文成绩" width="120" />

        <el-table-column prop="mathScore" label="数学成绩" width="120" />

         <el-table-column prop="engScore" label="英语成绩" width="120" />

          <el-table-column prop="javaScore" label="程序设计成绩" width="120" />
      


      <el-table-column prop="gmtCreate" label="添加时间" width="160"/>

      <el-table-column prop="sort" label="排序" width="100" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/student/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

   <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />



  </div>
</template>
<script>
//引入techer.js
import student from '@/api/edu/student'
export default {
  data() {
    return {
        //定义当前页面使用的变量和初始值   
    list:null,//拆线呢之后接口返回集合    
    page: 1,//当前页    
    limit: 10,
    total: 0,//总记录数   
    studentQuery:{},//条件封装对象   
    listLoading: false

    }
  
  },
  created() {
    //页面渲染之前执行，一般调用methods定义的方法
    this.getList()

  },
  methods:{
   // 调用具体方法   
      getList(page =1) {
        this.page = page
        student.getStudentListPage(this.page,this.limit,this.studentQuery)
        .then(response =>{
          //请求成功   response接口返回数据
          this.list = response.data.rows
          this.total = response.data.total
      
        })
        .catch(erro =>{
          console.log(erro)
        })
      },
      resetData() {
        //清空方法
        this.studentQuery = {}
        this.getList()

      },
      removeDataById(id){

        this.$confirm('此操作将永久删除讲师记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {//点击确定，执行then方法
          //调用删除的方法
          student.deleteDataById(id)
               .then(response =>{
                  this.$message({
                    type: 'success',
                    message: '删除成功!'
                      });
               this.getList()
               })
               .catch(erro =>{
               this.getList()
               })
        })

      }
    

  }

    
}
</script>