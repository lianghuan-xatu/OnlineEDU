<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="3" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>publish

    <el-form label-width="120px">
    <div class="ccInfo">
      <img :src="publishCourseInfo.cover">
      <div class="main">
        <h2>{{ publishCourseInfo.title }}</h2>
        <p class="gray"><span>共{{ publishCourseInfo.lessonNum }}课时</span></p>
        <p><span>所属分类：{{ publishCourseInfo.subjectLevelOne }} — {{ publishCourseInfo.subjectLevelTwo }}</span></p>
        <p>课程讲师：{{ publishCourseInfo.teacherName }}</p>
        <h3 class="red">￥{{ publishCourseInfo.price }}</h3>
      </div>
    </div>
      <div>
        <el-button @click="previous">返回修改</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="publish">最终发布</el-button>
      </div>
    </el-form>

  </div>
</template>

<script>
import course from '@/api/edu/course'
export default {
  data() {
    return {
      courseId: '',
      publishCourseInfo: {},
      saveBtnDisabled: false // 保存按钮是否禁用
    }
  },

  created() {
    if(this.$route.params && this.$route.params.id){
      this.courseId = this.$route.params.id
      this.getPublishCourseInfo()
    }
  },

  methods: {
    previous() {
 
      this.$router.push({ path: '/course/info/'+this.courseId })
    },
    getPublishCourseInfo() {
      course.getPublishCourseInfo(this.courseId).then(reponse =>{
        this.publishCourseInfo = reponse.data.coursePublishInfo
      })
    },
    publish() {

  this.$confirm('确认发布课程信息, 是否确定?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
      course.publishCourse(this.courseId)
      .then(reponse =>{
        this.$message({
        type: 'success',
        message: '发布成功'
      })
         this.$router.push({ path: '/course/list' })
      })
      .catch(() => {
     
      })
      
  })
    
    }
  }

}
</script>
<style scoped>
.ccInfo {
    background: #f5f5f5;
    padding: 20px;
    overflow: hidden;
    border: 1px dashed #DDD;
    margin-bottom: 40px;
    position: relative;
}
.ccInfo img {
    background: #d6d6d6;
    width: 500px;
    height: 278px;
    display: block;
    float: left;
    border: none;
}
.ccInfo .main {
    margin-left: 520px;
}

.ccInfo .main h2 {
    font-size: 28px;
    margin-bottom: 30px;
    line-height: 1;
    font-weight: normal;
}
.ccInfo .main p {
    margin-bottom: 10px;
    word-wrap: break-word;
    line-height: 24px;
    max-height: 48px;
    overflow: hidden;
}

.ccInfo .main p {
    margin-bottom: 10px;
    word-wrap: break-word;
    line-height: 24px;
    max-height: 48px;
    overflow: hidden;
}
.ccInfo .main h3 {
    left: 540px;
    bottom: 20px;
    line-height: 1;
    font-size: 28px;
    color: #d32f24;
    font-weight: normal;
    position: absolute;
}
</style>