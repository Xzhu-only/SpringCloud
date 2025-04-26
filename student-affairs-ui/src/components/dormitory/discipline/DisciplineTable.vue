<template>
  <el-card class="list-table">
    <el-table :data="recordList" max-height="550" style="width: 100%">
      <el-table-column label="宿舍名称" prop="dormitoryName"/>
      <el-table-column label="违纪类型" prop="incidentType"/>
      <el-table-column label="违纪详情" prop="incidentDetails" show-overflow-tooltip/>
      <el-table-column label="处罚措施" prop="punishment" show-overflow-tooltip/>
      <el-table-column label="违纪日期" prop="incidentDate"/>

      <el-table-column v-if="userStore.user.role !== 1" label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button type="warning" icon="Edit" size="small"
                     @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="你确定要删除该违纪记录吗？" @confirm="handleDelete(scope.row.id)">
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
import {deleteDormitoryDisciplineRecordByIdApi} from "@/api/dormitory.js";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['recordList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (recordId) => {
  const res = await deleteDormitoryDisciplineRecordByIdApi(recordId)
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