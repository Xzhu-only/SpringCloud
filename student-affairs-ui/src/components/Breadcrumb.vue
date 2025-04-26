<template>
  <el-breadcrumb separator="/" class="breadcrumb-layout">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item >{{ breadcrumbStr }}</el-breadcrumb-item>
  </el-breadcrumb>
  <el-divider/>
</template>

<script setup>
import {useRoute} from "vue-router";
import {computed} from "vue";

const route = useRoute()
const breadcrumbStr = computed(() => {
  const name = route.name
  if (name === 'User') {
    return '用户管理'
  } else if (name === 'Course') {
    return '课程管理'
  } else if (name === 'StudentCard') {
    return '学生证管理'
  } else if (name === 'StudentCampus') {
    return '校园卡管理'
  } else if (name === 'StudentScore') {
    return '成绩管理'
  } else if (name === 'StudentAward') {
    return '奖惩管理'
  } else if (name === 'StudentFinancial') {
    return '资助管理'
  } else if (name === 'StudentHealth') {
    return '健康记录'
  } else if (name === 'StudentDormitory') {
    return '宿舍查询'
  } else if (name === 'StudentRegistration') {
    return '新生登记'
  } else if (name === 'StudentSign') {
    return '新生签到'
  } else if (name === 'Dormitory') {
    return '宿舍管理'
  } else if (name === 'DormitoryClean') {
    return '宿舍卫生检查记录'
  } else if (name === 'DormitoryDiscipline') {
    return '宿舍违纪记录'
  } else if (name === 'DormitoryRepair') {
    return '宿舍报修'
  } else if (name === 'DormitoryStay') {
    return '宿舍留校'
  } else if (name === 'DormitoryRepairApply') {
    return '宿舍报修申请'
  } else if (name === 'DormitoryStayApply') {
    return '宿舍报修申请'
  } else if (name === 'StudentProcess') {
    return '报道流程'
  } else if (name === 'GraduateProcess') {
    return '毕业流程'
  } else if (name === 'Statistic') {
    return '数据分析'
  }
})
</script>

<style scoped>
.breadcrumb-layout {
  margin-top: 15px;
  margin-left: 15px;
  margin-bottom: -8px
}
</style>