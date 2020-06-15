<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程详情 开始 -->
    <section class="container">
      <section class="path-wrap txtOf hLh30">
        <a href="#" title class="c-999 fsize14">首页</a>
        \
        <a href="#" title class="c-999 fsize14">课程列表</a>
        \
        <span class="c-333 fsize14">{{course.subjectLevelOne}}</span>
      </section>
     <!-- 课程基本信息 开始 -->
<div>
    <article class="c-v-pic-wrap" style="height: 357px;">
        <section id="videoPlay" class="p-h-video-box">
            <img :src="course.cover" :alt="course.title" class="dis c-v-pic">
        </section>
    </article>
    <aside class="c-attr-wrap">
        <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
                <span class="c-fff fsize24">{{ course.title }}</span>
            </h2>
            <section class="c-attr-jg">
                <span class="c-fff">价格：</span>
                <b class="c-yellow" style="font-size:24px;">￥{{ course.price }}</b>
            </section>
            <section class="c-attr-mt c-attr-undis">
                <span class="c-fff fsize14">主讲： {{ course.teacherName }}&nbsp;&nbsp;&nbsp;</span>
            </section>
            <section class="c-attr-mt of">
                <span class="ml10 vam">
                    <em class="icon18 scIcon"/>
                    <a class="c-fff vam" title="收藏" href="#" >收藏</a>
                </span>
            </section>
            <section class="c-attr-mt">
                <a href="#" title="立即观看" class="comm-btn c-btn-3">立即观看</a>
            </section>
        </section>
    </aside>
    <aside class="thr-attr-box">
        <ol class="thr-attr-ol clearfix">
            <li>
                <p>&nbsp;</p>
                <aside>
                    <span class="c-fff f-fM">购买数</span>
                    <br>
                    <h6 class="c-fff f-fM mt10">{{ course.buyCount }}</h6>
                </aside>
            </li>
            <li>
                <p>&nbsp;</p>
                <aside>
                    <span class="c-fff f-fM">课时数</span>
                    <br>
                    <h6 class="c-fff f-fM mt10">{{ course.lessonNum }}</h6>
                </aside>
            </li>
            <li>
                <p>&nbsp;</p>
                <aside>
                    <span class="c-fff f-fM">浏览数</span>
                    <br>
                    <h6 class="c-fff f-fM mt10">{{ course.viewCount }}</h6>
                </aside>
            </li>
        </ol>
    </aside>
    <div class="clear"/>
</div>
<!-- /课程基本信息 结束 -->
      <!-- /课程封面介绍 -->
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="课程详情">课程详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">
              <!-- 课程详情介绍 开始 -->
<div>
    <h6 class="c-i-content c-infor-title">
        <span>课程介绍</span>
    </h6>
    <div class="course-txt-body-wrap">
        <section class="course-txt-body">
            <!-- 将内容中的html翻译过来 -->
            <p v-html="course.description">{{ course.description }}</p>
        </section>
    </div>
</div>
<!-- /课程详情介绍 结束 -->
                <!-- /课程介绍 --> 
               <!-- 课程大纲 开始-->
<div class="mt50">
    <h6 class="c-g-content c-infor-title">
        <span>课程大纲</span>
    </h6>
    <section class="mt20">
        <div class="lh-menu-wrap">
            <menu id="lh-menu" class="lh-menu mt10 mr10">
                <ul>
                    <!-- 课程章节目录 -->
                    <li v-for="chapter in chapterList" :key="chapter.id" class="lh-menu-stair">
                        <a :title="chapter.title" href="javascript: void(0)" class="current-1">
                            <em class="lh-menu-i-1 icon18 mr10"/>{{ chapter.title }}
                        </a>
                        <ol class="lh-menu-ol" style="display: block;">
                            <li v-for="video in chapter.videoVoList" :key="video.id" class="lh-menu-second ml30">
                                <a :href="'/player/'+video.videoSourceId" :title="video.title" target="_blank">
                                    <span v-if="video.free === true" class="fr">
                                        <i class="free-icon vam mr10">免费试听</i>
                                    </span>
                                    <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{ video.title }}
                                </a>
                            </li>
                        </ol>
                    </li>
                </ul>
            </menu>
        </div>
    </section>
    </div>
    <!-- /课程大纲 结束 -->
                <!-- /课程大纲 -->
              </article>
            </div>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
         <!-- 主讲讲师 开始-->
<div>
    <section class="c-infor-tabTitle c-tab-title">
        <a title href="javascript:void(0)">主讲讲师</a>
    </section>
    <section class="stud-act-list">
        <ul style="height: auto;">
            <li>
                <div class="u-face">
                    <a :href="'/teacher/'+course.teacherId" target="_blank">
                        <img :src="course.avatar" width="50" height="50" alt>
                    </a>
                </div>
                <section class="hLh30 txtOf">
                    <a :href="'/teacher/'+course.teacherId" class="c-333 fsize16 fl" target="_blank">{{ course.teacherName }}</a>
                </section>
                <section class="hLh20 txtOf">
                    <span class="c-999">{{ course.intro }}</span>
                </section>
            </li>
        </ul>
    </section>
</div>
<!-- /主讲讲师 结束 -->
          </div>
        </aside>
        <div class="clear"></div>
      </div>
    </section>
    <!-- /课程详情 结束 -->



  <div class="mt50 commentHtml">
    <div>
      <h6 class="c-c-content c-infor-title" id="i-art-comment">
        <span class="commentTitle">课程评论</span>
      </h6>
      <section class="lh-bj-list pr mt20 replyhtml">
        <ul>
          <li class="unBr">
            <aside class="noter-pic">
              <img width="50" height="50" class="picImg" src="~/assets/img/avatar-boy.gif">
              </aside>
            <div class="of">
              <section class="n-reply-wrap">
                <fieldset>
                  <textarea name="" v-model="comment.content" placeholder="输入您要评论的文字" id="commentContent"></textarea>
                </fieldset>
                <p class="of mt5 tar pl10 pr10">
                  <span class="fl "><tt class="c-red commentContentmeg" style="display: none;"></tt></span>
                  <input type="button" @click="addComment()" value="回复" class="lh-reply-btn">
                </p>
              </section>
            </div>
          </li>
        </ul>
      </section>
      <section class="">
          <section class="question-list lh-bj-list pr">
            <ul class="pr10">
              <li v-for="(comment,index) in data.items" v-bind:key="index">
                  <aside class="noter-pic">
                    <img width="50" height="50" class="picImg" :src="comment.avatar">
                    </aside>
                  <div class="of">
                    <span class="fl"> 
                    <font class="fsize12 c-blue"> 
                      {{comment.nickname}}</font>
                    <font class="fsize12 c-999 ml5">评论：</font></span>
                  </div>
                  <div class="noter-txt mt5">
                    <p>{{comment.content}}</p>
                  </div>
                  <div class="of mt5">
                    <span class="fr"><font class="fsize12 c-999 ml5">{{comment.gmtCreate}}</font></span>
                  </div>
                </li>
              
              </ul>
          </section>
        </section>
        
        <!-- 公共分页 开始 -->
        <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <a
            :class="{undisable: !data.hasPrevious}"
            href="#"
            title="首页"
            @click.prevent="gotoPage(1)">首</a>
            <a
            :class="{undisable: !data.hasPrevious}"
            href="#"
            title="前一页"
            @click.prevent="gotoPage(data.current-1)">&lt;</a>
            <a
            v-for="page in data.pages"
            :key="page"
            :class="{current: data.current == page, undisable: data.current == page}"
            :title="'第'+page+'页'"
            href="#"
            @click.prevent="gotoPage(page)">{{ page }}</a>
            <a
            :class="{undisable: !data.hasNext}"
            href="#"
            title="后一页"
            @click.prevent="gotoPage(data.current+1)">&gt;</a>
            <a
            :class="{undisable: !data.hasNext}"
            href="#"
            title="末页"
            @click.prevent="gotoPage(data.pages)">末</a>
            <div class="clear"/>
        </div>
        <!-- 公共分页 结束 -->
      </div>
    </div>
 
  </div>
</template>


    
<script>
import course from '@/api/edu/course'
import comment from '@/api/edu/comment'
export default {

     //和页面异步开始的
  asyncData({ params, error }) {
    return {courseId: params.id}
    
  },
  data() {
    return {
      data:{},
      page:1,
      limit:4,
      total:10,
      comment:{
        content:'',
        courseId:''
      },
      course:{},
      chapterList:[],
      isbuyCourse:false
    }
  },
  created() {
    this.courseId = this.$route.params.id
    this.initCourseInfo()
    this.initComment()
  },
  methods:{
    //获取课程详情
    initCourseInfo() {
      course.getById(this.courseId)
            .then(response => {
              this.course=response.data.data.course
              this.chapterList=response.data.data.chapterList
              this.isbuyCourse=response.data.data.isbuyCourse
            })
    },

    initComment(){
       comment.getPageList(this.page, this.limit, this.courseId).then(response => {
           this.data = response.data.data.resultMap
       })
    },
    addComment(){
        this.comment.courseId = this.courseId
        this.comment.teacherId = this.course.teacherId
        comment.addComment(this.comment).then(response => {
            if(response.data.success){
                this.comment.content = ''
                this.initComment()
            }
        })
    },
    gotoPage(page){
          comment.getPageList(page, this.limit,this.courseId).then(response => {
              this.data = response.data.data.resultMap
          })
      }
  }
  
};
</script>