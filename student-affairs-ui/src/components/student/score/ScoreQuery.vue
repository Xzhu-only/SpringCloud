<template>
  <el-card class="query-card">
    <el-form :inline="true" :model="queryForm" style="margin-bottom: -15px">
      <el-form-item v-if="userStore.user.role !== 1" label="学生">
        <el-select v-model="queryForm.studentId" placeholder="请选择查询的学生" style="width: 200px">
          <el-option v-for="item in studentList" :key="item.id" :label="item.username" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="课程">
        <el-select v-model="queryForm.courseId" placeholder="请选择查询的课程" style="width: 200px">
          <el-option v-for="item in courseList" :key="item.id" :label="item.courseName" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="学期">
        <el-select v-model="queryForm.semester" placeholder="请选择课程学期" style="width: 240px">
          <el-option label="2024-2025第一学期" value="2024-2025第一学期"/>
          <el-option label="2024-2025第二学期" value="2024-2025第二学期"/>
          <el-option label="2025-2026第一学期" value="2025-2026第一学期"/>
          <el-option label="2025-2026第二学期" value="2025-2026第二学期"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-form-item>
          <el-button circle type="info" icon="Refresh" @click="resetQuery"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">查询</el-button>
        </el-form-item>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import {useRoute} from "vue-router";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['studentList', 'courseList', 'queryForm', 'pageParam'])
const emit = defineEmits()

const route = useRoute()
//处理查询按钮响应
const handleQuery = () => {
  emit('queryAgain', 1, props.pageParam.pageSize)
}
//处理重置按钮响应
const resetQuery = () => {
  props.queryForm.studentId = ''
  props.queryForm.courseId = ''
  props.queryForm.semester = ''
  emit('queryAgain', 1, props.pageParam.pageSize)
}
</script>

<style scoped>
.query-card{
  margin-top: -20px;
  margin-bottom: 5px;
}
</style>