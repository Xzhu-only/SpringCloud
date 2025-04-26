<template>
  <el-card class="query-card">
    <el-form :inline="true" :model="queryForm" style="margin-bottom: -15px">
      <el-form-item label="宿舍名称">
        <el-input v-model="queryForm.dormitoryName" placeholder="请输入查询的宿舍名称" clearable />
      </el-form-item>
      <el-form-item v-if="userStore.user.role === 0" label="负责老师">
        <el-select v-model="queryForm.teacherId" placeholder="请选择负责老师" style="width: 240px">
          <el-option v-for="item in teacherList" :key="item.id" :label="item.username" :value="item.id"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-form-item>
          <el-button circle type="info" icon="Refresh" @click="resetQuery"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">查询</el-button>
        </el-form-item>
        <el-form-item v-if="userStore.user.role === 0">
          <el-button type="success" icon="Plus" @click="handleAdd">添加</el-button>
        </el-form-item>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['teacherList','queryForm', 'pageParam'])
const emit = defineEmits()

//处理查询按钮响应
const handleQuery = () => {
  emit('queryAgain', 1, props.pageParam.pageSize)
}
//处理重置按钮响应
const resetQuery = () => {
  props.queryForm.dormitoryName = ''
  props.queryForm.teacherId = ''
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