<template>
  <el-card class="query-card">
    <el-button type="primary" icon="Plus" @click="handleAdd">宿舍入住</el-button>
  </el-card>
</template>

<script setup>
import {useUserStore} from "@/stores/user.js";

const props = defineProps(['queryForm', 'pageParam'])
const emit = defineEmits()

//处理查询按钮响应
const handleQuery = () => {
  emit('queryAgain', 1, props.pageParam.pageSize)
}
//处理重置按钮响应
const resetQuery = () => {
  props.queryForm.cardNumber = ''
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