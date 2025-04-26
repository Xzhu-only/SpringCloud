<template>
  <el-card class="list-table">
    <el-table :data="stayList" max-height="550" style="width: 100%">
      <el-table-column label="留校学生" prop="studentName"/>
      <el-table-column label="留校宿舍" prop="dormitoryName"/>
      <el-table-column label="起始日期" prop="stayPeriodStart"/>
      <el-table-column label="结束日期" prop="stayPeriodEnd"/>
      <el-table-column label="留校原因" prop="reason" show-overflow-tooltip/>
      <el-table-column label="审批状态" prop="approvalStatus">
        <template #default="scope">
          <el-tag v-if="scope.row.approvalStatus === '待审批'" type="warning">待审批</el-tag>
          <el-tag v-if="scope.row.approvalStatus === '批准'" type="success">批准</el-tag>
          <el-tag v-if="scope.row.approvalStatus === '拒绝'" type="danger">拒绝</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button v-if="scope.row.approvalStatus === '待审批'" type="primary" icon="Select" size="small"
                     @click="handleEdit(scope.row, '批准')">批准</el-button>
          <el-button v-if="scope.row.approvalStatus === '待审批'" type="danger" icon="Close" size="small"
                     @click="handleEdit(scope.row, '拒绝')">拒绝</el-button>
          <el-popconfirm v-if="scope.row.approvalStatus !== '待审批'" title="你确定要删除该留校申请吗？" @confirm="handleDelete(scope.row.id)">
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
import {
  deleteDormitoryStayRequestByIdApi,
  editDormitoryStayRequestByIdApi
} from "@/api/dormitory.js";

const props = defineProps(['stayList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = async (row, state) => {
  const form = {
    id: row.id,
    studentId: row.studentId,
    dormitoryId: row.dormitoryId,
    reason: row.reason,
    approvalStatus: state,
  }
  const res = await editDormitoryStayRequestByIdApi(form)
  if (res.code === 200) {
    ElMessage.success('操作成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
//处理删除按钮响应
const handleDelete = async (stayId) => {
  const res = await deleteDormitoryStayRequestByIdApi(stayId)
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