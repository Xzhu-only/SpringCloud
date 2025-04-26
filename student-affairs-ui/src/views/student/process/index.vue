<template>
  <h2 style="text-align: center">新生报道流程</h2>
  <el-steps  align-center finish-status="success">
    <el-step title="报道登记" status="process">
      <template #description>
        <el-button plain size="small" type="warning" @click="toPath('/student/registration')">去登记</el-button>
      </template>
    </el-step>
    <el-step title="新生签到" status="process">
      <template #description>
        <el-button plain size="small" type="success" @click="toPath('/student/sign')">去签到</el-button>
      </template>
    </el-step>
    <el-step title="学生证办理" status="process">
      <template #description>
        <el-button plain size="small" type="primary" @click="toPath('/student/card')">去办理</el-button>
      </template>
    </el-step>
    <el-step title="校园卡办理" status="process">
      <template #description>
        <el-button plain size="small" type="warning" @click="toPath('/student/campus')">去办理</el-button>
      </template>
    </el-step>
  </el-steps>
</template>

<script setup>
import {useRouter} from "vue-router";

const router = useRouter()

//根据传入参数跳转页面
const toPath = (route) => {
  router.push(route)
}
</script>

<style scoped>

</style>