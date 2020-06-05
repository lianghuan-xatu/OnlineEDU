<template>
  <div class="app-container">
      <h2 style="text-align: center;">编辑Banner信息</h2>


<el-form label-width="120px">

        <el-form-item label="Banner标题">
            <el-input v-model="bannerInfo.title" placeholder=" 请填写Banner标题"/>
        </el-form-item>

        <el-form-item label="排序">
             <el-input-number v-model="bannerInfo.sort" :min="0" controls-position="right"/>
        </el-form-item>



    <el-form-item label="链接地址">
        <el-select v-model="bannerInfo.linkUrl" clearable placeholder="请选择链接地址">
          <el-option :value="'/teacher'" label="讲师"/>
          <el-option :value="'/course'" label="课程"/>
        </el-select>
      </el-form-item>




        <!-- 课程封面-->
        <el-form-item label="Banner封面">

            <el-upload
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                :action="BASE_API+'/eduOss/uploadAvatar'"
                class="avatar-uploader">
                <img :src="bannerInfo.imageUrl">
            </el-upload>

        </el-form-item>


        <el-form-item>
            <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
        </el-form-item>
        </el-form>
        
  </div>
</template>

<script>
import banner from '@/api/edu/banner'



const defaultForm = {
  id: '',
  title: '',
  imageUrl: '/static/02.jpg',
  linkUrl: '',
  sort: ''

}

export default {
  data() {
    return {
      bannerId: '',
      bannerInfo: defaultForm,
      BASE_API: process.env.BASE_API,
      saveBtnDisabled: false // 保存按钮是否禁用
    }
  },
  components: {
     
  },
  watch: {
    $route(to, from) {
      console.log('watch $route')
      this.init()
      console.log('init')
    }
  },
  created() {
    this.init()
  },

  methods: {

    init() {
      if (this.$route.params && this.$route.params.id) {
        this.bannerId= this.$route.params.id
        this.getBannerInfoById()
      } else {
        this.bannerInfo = { ...defaultForm }
      }
    },

          //添加课程
        addBanner() {
            banner.addBannerInfo(this.bannerInfo)
                .then(response => {
                    //提示
                    this.$message({
                        type: 'success',
                        message: '添加Banner信息成功!'
                    });
                    //跳转到第二步
                    this.$router.push({path:'/banner/list/'})
                })
        },
        //修改课程
        updateBanner() {
            banner.updateBannerInfo(this.bannerInfo)
                .then(response => {
                     //提示
                    this.$message({
                        type: 'success',
                        message: '修改Banner信息成功!'
                    });
                    //
                    this.$router.push({path:'/banner/list/'})
                })
        },
        saveOrUpdate() {
           //判断添加还是修改
           if(!this.bannerInfo.id) {
               //添加
               this.addBanner()
           } else {
               this.updateBanner()
           }
        },
    handleAvatarSuccess(res,file) {
        this.bannerInfo.imageUrl = res.data.url

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
    getBannerInfoById() {
        banner.getBannerInfoByBannerId(this.bannerId).then(response =>{
            this.bannerInfo = response.data.banner
        })

    }
  }
}

</script>
