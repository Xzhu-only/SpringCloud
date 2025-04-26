<template>
  <el-card class="query-card">
    <el-form :inline="true" :model="queryForm" style="margin-bottom: -15px">
      <el-form-item label="课程名称">
        <el-input v-model="queryForm.courseName" placeholder="请输入查询的课程名称" clearable />
      </el-form-item>
      <el-form-item label="课程编号">
        <el-input v-model="queryForm.courseCode" placeholder="请输入查询的课程编号" clearable />
      </el-form-item>

      <el-form-item>
        <el-form-item>
          <el-button circle type="info" icon="Refresh" @click="resetQuery"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">查询</el-button>
        </el-form-item>
        <el-form-item v-if="userStore.user.role !== 1">
          <el-button type="success" icon="Plus" @click="handleAdd">添加</el-button>
        </el-form-item>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import {useRoute} from "vue-router";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['queryForm', 'pageParam'])
const emit = defineEmits()

const route = useRoute()
//处理查询按钮响应
const handleQuery = () => {
  emit('queryAgain', 1, props.pageParam.pageSize)
}
//处理重置按钮响应
const resetQuery = () => {
  props.queryForm.courseName = ''
  props.queryForm.courseCode = ''
  emit('queryAgain', 1, props.pageParam.pageSize)
}
//处理添加按钮响应
const handleAdd = () => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = true
  props.pageParam.row = {}
}
</script>

<style scoped>
.query-card{
  margin-top: -20px;
  margin-bottom: 5px;
}
</style>