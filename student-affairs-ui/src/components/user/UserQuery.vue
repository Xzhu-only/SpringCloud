<template>
  <el-card class="query-card">
    <el-form :inline="true" :model="queryForm" style="margin-bottom: -15px">
      <el-form-item label="用户名">
        <el-input v-model="queryForm.username" placeholder="请输入查询的用户名" clearable />
      </el-form-item>
      <el-form-item label="用户角色">
        <el-select style="width: 200px" v-model="queryForm.role" placeholder="请选择查询的用户角色" clearable>
          <el-option label="管理员" :value="0" />
          <el-option label="学生" :value="1" />
          <el-option label="教师" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="用户状态">
        <el-select style="width: 200px" v-model="queryForm.state" placeholder="请选择查询的用户状态" clearable>
          <el-option label="正常" :value="0" />
          <el-option label="封禁中" :value="1" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-form-item>
          <el-button circle type="info" icon="Refresh" @click="resetQuery"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="success" icon="Plus" @click="handleAdd">添加</el-button>
        </el-form-item>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import {useRoute} from "vue-router";

const props = defineProps(['queryForm', 'pageParam'])
const emit = defineEmits()

const route = useRoute()
//处理查询按钮响应
const handleQuery = () => {
  emit('queryAgain', 1, props.pageParam.pageSize)
}
//处理重置按钮响应
const resetQuery = () => {
  props.queryForm.username = ''
  props.queryForm.role = ''
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