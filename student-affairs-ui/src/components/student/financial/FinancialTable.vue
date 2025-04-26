<template>
  <el-card class="list-table">
    <el-table :data="aidList" max-height="550" style="width: 100%">
      <el-table-column label="资助学生" prop="studentName"/>
      <el-table-column label="资助类型" prop="aidType"/>
      <el-table-column label="资助金额" prop="aidAmount">
        <template #default="scope">
          {{scope.row.aidAmount}}元
        </template>
      </el-table-column>
      <el-table-column label="资助学期" prop="semester"/>
      <el-table-column label="资助来源" prop="aidSource"/>
      <el-table-column label="资助状态" prop="state">
        <template #default="scope">
          <el-tag v-if="scope.row.state === '待领取'" type="warning">待领取</el-tag>
          <el-tag v-if="scope.row.state === '已领取'" type="success">已领取</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" prop="remark" show-overflow-tooltip/>
      <el-table-column label="资助时间" prop="createTime"/>

      <el-table-column label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button v-if="userStore.user.role !== 1" type="warning" icon="Edit" size="small"
                     @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm v-if="userStore.user.role !== 1" title="你确定要删除该资助记录吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" icon="Delete" size="small" >删除</el-button>
            </template>
          </el-popconfirm>

          <el-button v-if="userStore.user.role === 1" type="primary" icon="Select" size="small"
                     :disabled="scope.row.state === '已领取'" @click="handleReceive(scope.row)">领取</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
import {deleteStudentFinancialAidByIdApi, editStudentFinancialAidByIdApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['aidList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (aidId) => {
  const res = await deleteStudentFinancialAidByIdApi(aidId)
  if (res.code === 200) {
    ElMessage.success('删除成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
//处理领取响应
const handleReceive = async (row) => {
  row.state = '已领取'
  const res = await editStudentFinancialAidByIdApi(row)
  if (res.code === 200) {
    ElMessage.success("领取成功！")
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
</script>

<style scoped>
.list-table {
  height: 100%;
}
</style>