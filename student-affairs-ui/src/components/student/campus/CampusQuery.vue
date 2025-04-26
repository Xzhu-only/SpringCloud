<template>
  <el-card class="query-card">
    <el-form v-if="userStore.user.role !== 1" :inline="true" :model="queryForm" style="margin-bottom: -15px">
      <el-form-item label="校园卡编号">
        <el-input v-model="queryForm.cardNumber" placeholder="请输入查询的校园卡编号" clearable />
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

    <el-button v-if="userStore.user.role === 1" type="primary" icon="Plus" @click="handleAdd">办理校园卡</el-button>
  </el-card>
</template>

<script setup>
import {useUserStore} from "@/stores/user.js";

const props = defineProps(['queryForm', 'pageParam'])
const emit = defineEmits()

const userStore = useUserStore()
//处理查询按钮响应
const handleQuery = () => {
  emit('queryAgain', 1, props.pageParam.pageSize)
}
//处理重置按钮响应
const resetQuery = () => {
  props.queryForm.cardNumber = ''
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