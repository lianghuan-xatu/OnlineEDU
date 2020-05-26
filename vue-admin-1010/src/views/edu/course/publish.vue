<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="3" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>publish

    <el-form label-width="120px">

      <el-form-item>
        <el-button @click="previous">返回修改</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="publish">最终发布</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  data() {
    return {
      saveBtnDisabled: false // 保存按钮是否禁用
    }
  },

  created() {
    console.log('chapter created')
  },

  methods: {
    previous() {
      console.log('previous')
      this.$router.push({ path: '/edu/course/info/1' })
    },

    publish() {
      console.log('next')
      this.$router.push({ path: '/edu/course/list' })
    }
  }
}
</script>