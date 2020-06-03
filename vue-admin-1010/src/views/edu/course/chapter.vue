<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>

    <el-form label-width="120px">
 
      <el-form-item>
     <el-button type="text" @click="dialogChapterFormVisible = true">添加章节</el-button>
<!-- 章节 -->
<ul class="chanpterList">
    <li
        v-for="chapter in this.chapterNestedList"
        :key="chapter.id">
        <p>
            {{ chapter.title }} 


            <span class="acts">
              <el-button type="text" @click="openVideo(chapter.id)">添加课时</el-button>
              <el-button type="text" @click="editChapter(chapter.id)">编辑</el-button>
              <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
            </span>
        </p>
        

        <!-- 视频 -->
        <ul class="chanpterList videoList">
            <li
                v-for="video in chapter.videoVoList"
                :key="video.id">
                <p>{{ video.title }}
            <span class="acts">
              <el-button type="text" @click="editVideo(video.id)">编辑</el-button>
              <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
            </span>
                </p>
            </li>
        </ul>
    </li>
</ul>
<div>
    <el-button @click="previous">上一步</el-button>
    <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
</div>
      </el-form-item>
    </el-form>


<!-- 添加和修改章节表单 -->
<el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
    <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
            <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="章节排序">
            <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
    </div>
</el-dialog>


<!-- 添加和修改课时表单 -->
<el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
  <el-form :model="video" label-width="120px">
    <el-form-item label="课时标题">
      <el-input v-model="video.title"/>
    </el-form-item>
    <el-form-item label="课时排序">
      <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
    </el-form-item>
    <el-form-item label="是否免费">
      <el-radio-group v-model="video.free">
        <el-radio :label="true">免费</el-radio>
        <el-radio :label="false">默认</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="上传视频">
    <el-upload
           :on-success="handleVodUploadSuccess"
           :on-remove="handleVodRemove"
           :before-remove="beforeVodRemove"
           :on-exceed="handleUploadExceed"
           :file-list="fileList"
           :action="BASE_API+'/eduVod/uploadVideo'"
           :limit="1"
           class="upload-demo">
    <el-button size="small" type="primary">上传视频</el-button>
    <el-tooltip placement="right-end">
        <div slot="content">最大支持1G，<br>
            支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
            GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
            MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
            SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
        <i class="el-icon-question"/>
    </el-tooltip>
    </el-upload>
</el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
    <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
  </div>
</el-dialog> 







  </div>
</template>


<script>
import chapter from '@/api/edu/chapter'
import video from '@/api/edu/video'

export default {
  data() {
    return {
        courseId: '',
        chapterNestedList: [],
        dialogChapterFormVisible:false,//章节弹框
        chapter: {
            id: '',
            title: '',
            sort: 0,
            courseId: ''
        },
        dialogVideoFormVisible:false, //小节弹框
        saveBtnDisabled: false, // 保存按钮是否禁用

        saveVideoBtnDisabled: false, // 课时按钮是否禁用
        dialogVideoFormVisible: false, // 是否显示课时表单
        chapterId: '', // 课时所在的章节id
        video: {// 课时对象
            title: '',
            chapterId: '',
            sort: 0,
            free: 0,
            videoSourceId: '',
            videoOriginalName:''//视频名称
            },
      fileList: [],//文件上传列表
      BASE_API: process.env.BASE_API // 接口API地址



    }
  },
  created() {
    console.log('chapter created')
    this.init()
   },

  methods: {
    init() {
          if(this.$route.params && this.$route.params.id){
              this.courseId = this.$route.params.id 
              this.getNestedListByCourseId()     
          }
         
      },
    getNestedListByCourseId() {
          chapter.getNestedTreeList(this.courseId).then(response =>{
              this.chapterNestedList = response.data.items
          }) 
      },
    previous() {
      console.log('previous')
      this.$router.push({ path: '/course/info/'+this.courseId })
    },

    next() {
      console.log('next')
      this.$router.push({ path: '/course/publish/'+this.courseId })
    },
    saveOrUpdate() {
      this.saveBtnDisabled = true
      if(!this.chapter.id){
        this.save()
      }else{
        this.update()
      }
    },
    save() {
      this.chapter.courseId = this.courseId
      chapter.addChapter(this.chapter)
      .then(response =>{
          this.$message({
            type: 'success',
            message: '保存成功！'
          })
          this.helpSave()
      }).catch(response =>{
        this.$message({
            type: 'error',
            message: '保存失败！'
          })
      })
      
    },
    editChapter(chapterId) {
      this.dialogChapterFormVisible = true
        chapter.getChapterById(chapterId)
        .then(response =>{
          this.chapter = response.data.chapter
        })

    },
    update() {
    
      chapter.updateChapterById(this.chapter)
      .then(response =>{
          this.$message({
            type: 'success',
            message: '更新成功！'
          })
          this.helpSave()
      }).catch(response =>{
        this.$message({
            type: 'error',
            message: '更新失败！'
          })
      })

    },
    helpSave() {
      this.dialogChapterFormVisible = false //保存成功关闭弹框   
      this.getNestedListByCourseId() //刷新数据
      this.chapter.id = ''
      this.chapter.title = '' //重置标题
      this.chapter.sort = 0 //重置sort
      this.saveBtnDisabled = false  //关闭保存禁用     
    },
  removeChapter(chapterId) {
  this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return chapter.removeChapterById(chapterId)
  }).then(() => {
    this.getNestedListByCourseId()// 刷新列表
    this.$message({
      type: 'success',
      message: '删除成功!'
    })
  }).catch((response) => { // 失败
    if (response === 'cancel') {
      this.$message({
        type: 'info',
        message: '已取消删除'
      })
    } else {
      this.$message({
        type: 'error',
        message: response.message
      })
    }
  })
},
saveOrUpdateVideo() {
  this.saveVideoBtnDisabled = true
  if (!this.video.id) {
    this.saveDataVideo()
  } else {
    this.updateDataVideo()
  }
},

saveDataVideo() {
  this.video.courseId = this.courseId
  video.addVideoInfo(this.video).then(response => {
    this.$message({
      type: 'success',
      message: '保存成功!'
    })
    this.helpSaveVideo()
  })
},

updateDataVideo() {

  video.updateVideoById(this.video).then(response => {
    this.$message({
      type: 'success',
      message: '更新成功!'
    })
    this.helpSaveVideo()
  })


},

helpSaveVideo() {
  this.dialogVideoFormVisible = false// 如果保存成功则关闭对话框
  this.getNestedListByCourseId()// 刷新列表
  this.video.title = ''// 重置章节标题
  this.video.sort = 0// 重置章节标题
  this.video.videoSourceId = ''// 重置视频资源id
  this.saveVideoBtnDisabled = false
  this.fileList = []
},

 openVideo(chapterId) {
            //弹框
            this.dialogVideoFormVisible = true
            //设置章节id
            this.video.chapterId = chapterId
        },
editVideo(videoId) {
  this.dialogVideoFormVisible = true
  video.getVideoInfoById(videoId).then(response => {
    this.video = response.data.item
   
  })
}
,

removeVideo(videoId) {
  this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return video.removeVideoById(videoId)
  }).then(() => {
    this.getNestedListByCourseId()// 刷新列表
    this.$message({
      type: 'success',
      message: '删除成功!'
    })
  }).catch((response) => { // 失败
    if (response === 'cancel') {
      this.$message({
        type: 'info',
        message: '已取消删除'
      })
    }
  })
},


//成功回调
handleVodUploadSuccess(response, file, fileList) {
  this.video.videoSourceId = response.data.videoId
  this.video.videoOriginalName = file.name//视频名称
},
//视图上传多于一个视频
handleUploadExceed(files, fileList) {
  this.$message.warning('想要重新上传视频，请先删除已上传的视频')
},
beforeVodRemove(file,fileList) {
  return this.$confirm('确定移除 ${this.file.name} ?')

},
handleVodRemove() {

  //调用接口中的删除方法
  chapter.removeVideo(this.video.videoSourceId)
  .then(response =>{
        this.$message({
      type: 'success',
      message: '删除成功!'
 
    })
     this.fileList = [],
     this.video.videoSourceId = '',
     this.video.videoOriginalName = ''
  })

}
  }
}
</script>
<style scoped>
.chanpterList{
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
}
.chanpterList li{
  position: relative;
}
.chanpterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chanpterList .acts {
    float: right;
    font-size: 14px;
}

.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}

</style>