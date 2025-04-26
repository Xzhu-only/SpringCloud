<template>
  <el-card class="query-card">
    <el-form :inline="true" :model="queryForm" style="margin-bottom: -15px">
      <el-form-item v-if="userStore.user.role !== 1" label="学生">
        <el-select v-model="queryForm.studentId" placeholder="请输入查询的学生" style="width: 200px">
          <el-option v-for="item in studentList" :key="item.id" :label="item.username" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="资助类型">
        <el-select v-model="queryForm.aidType" placeholder="请选择资助类型" style="width: 200px">
          <el-option label="奖学金" value="奖学金"/>
          <el-option label="助学金" value="助学金"/>
        </el-select>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryForm.state" placeholder="请选择状态" style="width: 200px">
          <el-option label="待领取" value="待领取"/>
          <el-option label="已领取" value="已领取"/>
        </el-select>
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
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['studentList', 'queryForm', 'pageParam'])
const emit = defineEmits()

//处理查询按钮响应
const handleQuery = () => {
  emit('queryAgain', 1, props.pageParam.pageSize)
}
//处理重置按钮响应
const resetQuery = () => {
  props.queryForm.studentId = ''
  props.queryForm.aidType = ''
  props.queryForm.state = ''
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