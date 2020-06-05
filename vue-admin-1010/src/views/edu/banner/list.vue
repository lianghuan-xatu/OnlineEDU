<template>
  <div class="app-container">
    Banner列表
      <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="bannerQuery.title" placeholder="Banner名称"/>
      </el-form-item>


      <el-form-item label="添加时间">
        <el-date-picker
          v-model="bannerQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="bannerQuery.end"
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

      <el-table-column prop="title" label="Banner名称" width="80" />


      <el-table-column
        label="图片"
        width="800"
        align="center">
        <template slot-scope="scope">
          <img :src="scope.row.imageUrl">
        </template>
      </el-table-column>

      <el-table-column prop="gmtCreate" label="添加时间" width="160"/>



      <el-table-column label="操作" width="450"  align="center">
        <template slot-scope="scope">
          <router-link :to="'/banner/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑Banner基本信息</el-button>
          </router-link>
    
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除Banner信息</el-button>
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
import banner from '@/api/edu/banner'
export default {
  data() {
    return {
        //定义当前页面使用的变量和初始值   
    list:null,//拆线呢之后接口返回集合    
    page: 1,//当前页    
    limit: 10,
    total: 0,//总记录数   
    bannerQuery:{
        title: '',
        begin: '',
        end: ''
    },//条件封装对象   
    listLoading: false
    }
  
  },
  created() {
    //页面渲染之前执行，一般调用methods定义的方法
    this.bannerQuery = {}
    this.getList()

  },
  methods:{
   // 调用具体方法   
      getList(page =1) {
        this.page = page
        banner.getBannerListPage(this.page,this.limit,this.bannerQuery)
        .then(response =>{
          //请求成功   response接口返回数据
          this.list = response.data.list
          this.total = response.data.total
       

        })
        .catch(erro =>{
          console.log(erro)
        })
      },
      resetData() {
        //清空方法
        this.bannerQuery = {}
        this.getList()

      },
      removeDataById(id){

        this.$confirm('此操作将永久删除Banner信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {//点击确定，执行then方法
          //调用删除的方法
          banner.deleteDataById(id)
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