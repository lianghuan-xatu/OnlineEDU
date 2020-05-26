<template>
  <div class="app-container">
      <h2 style="text-align: center;">发布新课程</h2>
<el-steps :active="1" align-center>
  <el-step title="填写课程基本信息"></el-step>
  <el-step title="创建课程大纲"></el-step>
  <el-step title="最终发布"></el-step>
</el-steps>

<el-form label-width="120px">

        <el-form-item label="课程标题">
            <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
        </el-form-item>

        <!-- 所属分类 TODO -->
        <el-form-item label="课程分类">
            <el-select
                v-model="courseInfo.subjectParentId"
                placeholder="一级分类" @change="subjectLevelOneChanged">

                <el-option
                    v-for="subject in subjectOneList"
                    :key="subject.id"
                    :label="subject.title"
                    :value="subject.id"/>

            </el-select>

            <!-- 二级分类 -->
            <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
                <el-option
                    v-for="subject in subjectTwoList"
                    :key="subject.id"
                    :label="subject.title"
                    :value="subject.id"/>
            </el-select>
        </el-form-item>


        <!-- 课程讲师 TODO -->
        <!-- 课程讲师 -->
        <el-form-item label="课程讲师">
        <el-select
            v-model="courseInfo.teacherId"
            placeholder="请选择">

            <el-option
                v-for="teacher in teacherList"
                :key="teacher.id"
                :label="teacher.name"
                :value="teacher.id"/>

        </el-select>
        </el-form-item>

        <el-form-item label="总课时">
            <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
        </el-form-item>

        <!-- 课程简介 TODO -->
        <!-- 课程简介-->
         <el-form-item label="课程简介">
            <tinymce :height="300" v-model="courseInfo.description"/>
        </el-form-item>

        <!-- 课程封面 TODO -->
        <!-- 课程封面-->
        <el-form-item label="课程封面">

            <el-upload
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                :action="BASE_API+'/eduOss/uploadAvatar'"
                class="avatar-uploader">
                <img :src="courseInfo.cover">
            </el-upload>

        </el-form-item>

        <el-form-item label="课程价格">
            <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元
        </el-form-item>

        <el-form-item>
            <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
        </el-form-item>
        </el-form>
        
  </div>
</template>

<script>
import course from '@/api/edu/course'
import teacher from '@/api/edu/teacher'
import subject from '@/api/edu/subject'
import Tinymce from '@/components/Tinymce'

const defaultForm = {
  title: '',
  subjectId: '',
  subjectParentId: '',
  teacherId: '',
  lessonNum: 0,
  description: '',
  cover: '/static/02.jpg',
  price: 0
}

export default {
  data() {
    return {
      courseId: '',
      courseInfo: defaultForm,
      teacherList: [],
      subjectOneList: [],
      subjectTwoList: [],
      BASE_API: process.env.BASE_API,
      saveBtnDisabled: false // 保存按钮是否禁用
    }
  },
  components: {
      Tinymce
  },
  watch: {
    $route(to, from) {
      console.log('watch $route')
      this.init()
    }
  },

  created() {
    console.log('info created')
    this.init(),
    this.getTeacherList()
    this.getSubjectOne()
  },

  methods: {

    init() {
      if (this.$route.params && this.$route.params.id) {
        this.courseId = this.$route.params.id
        this.getCourseInfoByCourseId()
      } else {
        this.courseInfo = { ...defaultForm }
      }
    },

          //添加课程
        addCourse() {
            course.addCourseInfo(this.courseInfo)
                .then(response => {
                    //提示
                    this.$message({
                        type: 'success',
                        message: '添加课程信息成功!'
                    });
                    //跳转到第二步
                    this.$router.push({path:'/course/chapter/'+response.data.courseId})
                })
        },
        //修改课程
        updateCourse() {
            course.updateCourseInfo(this.courseInfo)
                .then(response => {
                     //提示
                    this.$message({
                        type: 'success',
                        message: '修改课程信息成功!'
                    });
                    //跳转到第二步
                    this.$router.push({path:'/course/chapter/'+this.courseId})
                })
        },
        saveOrUpdate() {
           //判断添加还是修改
           if(!this.courseInfo.id) {
               //添加
               this.addCourse()
           } else {
               this.updateCourse()
           }
        }
    ,
    getTeacherList() {
        teacher.getAllTeacher().then(response =>{
            this.teacherList = response.data.teacherList
        })
    },
    getSubjectOne() {
        subject.getSubjectList()
        .then(response =>{
            this.subjectOneList = response.data.list
        })        
    },
    subjectLevelOneChanged(value) {
        for(var i =0;i<this.subjectOneList.length;i++){
            if(this.subjectOneList[i].id === value){
                this.subjectTwoList = this.subjectOneList[i].twoSubjectList
                this.courseInfo.subjectId = ''//清空二级分类  
            }
        }

    },
    handleAvatarSuccess(res,file) {
        this.courseInfo.cover = res.data.url

    },
    beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg'
            const isLt2M = file.size / 1024 / 1024 < 2

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!')
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!')
            }
            return isJPG && isLt2M
    },
    getCourseInfoByCourseId() {
        course.fetchCourseInfo(this.courseId).then(response =>{
            this.courseInfo = response.data.courseInfoVo
        })

    }
  }
}
</script>
<style scoped>
.tinymce-container {
    line-height:29px;
}
</style>