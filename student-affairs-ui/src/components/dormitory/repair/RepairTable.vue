<template>
  <el-card class="list-table">
    <el-table :data="repairList" max-height="550" style="width: 100%">
      <el-table-column label="报修宿舍" prop="dormitoryName"/>
      <el-table-column label="报修类型" prop="issueType"/>
      <el-table-column label="问题描述" prop="issueDescription" show-overflow-tooltip/>
      <el-table-column label="申请状态" prop="repairStatus">
        <template #default="scope">
          <el-tag v-if="scope.row.repairStatus === '待处理'" type="warning">待处理</el-tag>
          <el-tag v-if="scope.row.repairStatus === '已完成'" type="success">已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="申请日期" prop="requestDate"/>

      <el-table-column label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button v-if="scope.row.repairStatus === '待处理'" type="primary" icon="Select" size="small"
                     @click="handleEdit(scope.row, '已完成')">检修</el-button>
          <el-popconfirm v-if="scope.row.repairStatus === '已完成'" title="你确定要删除该保修申请吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" icon="Delete" size="small" >删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
import {ElMessage} from "element-plus";
import {deleteDormitoryRepairRequestByIdApi, editDormitoryRepairRequestByIdApi} from "@/api/dormitory.js";

const props = defineProps(['repairList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = async (row, state) => {
  row.repairStatus = state
  const res = await editDormitoryRepairRequestByIdApi(repairStatus)
  if (res.code === 200) {
    ElMessage.success('操作成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
//处理删除按钮响应
const handleDelete = async (repairId) => {
  const res = await deleteDormitoryRepairRequestByIdApi(repairId)
  if (res.code === 200) {
    ElMessage.success('删除成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
</script>

<style scoped>
.list-table {
  height: 100%;
}
</style>