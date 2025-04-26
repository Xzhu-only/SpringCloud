<template>
  <el-card class="list-table">
    <h3 style="margin-top: -6px">我的学生证：</h3>
    <el-table :data="cardList" max-height="550" style="width: 100%">
      <el-table-column label="学生证编号" prop="cardNumber"/>
      <el-table-column label="学生姓名" prop="studentName"/>
      <el-table-column label="办理状态" prop="state">
        <template #default="scope">
          <el-tag v-if="scope.row.state === '申请中'" type="warning">申请中</el-tag>
          <el-tag v-if="scope.row.state === '已发放'" type="success">已发放</el-tag>
          <el-tag v-if="scope.row.state === '已失效'" type="danger">已失效</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="申请日期" prop="applicationDate"/>
      <el-table-column label="发放时间" prop="issueDate"/>
      <el-table-column label="过期时间" prop="expirationDate"/>
      <el-table-column label="备注" prop="remarks" show-overflow-tooltip/>

      <el-table-column v-if="userStore.user.role !== 1" label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button type="warning" icon="Select" size="small"
                     @click="handleEdit(scope.row)">审核</el-button>
          <el-popconfirm title="你确定要删除该学生证吗？" @confirm="handleDelete(scope.row.id)">
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
import {deleteStudentCardByIdApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['cardList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (cardId) => {
  const res = await deleteStudentCardByIdApi(cardId)
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