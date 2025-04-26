<template>
  <el-card class="list-table">
    <el-table :data="cleanList" max-height="550" style="width: 100%">
      <el-table-column label="宿舍名称" prop="dormitoryName"/>
      <el-table-column label="卫生状况" prop="cleanlinessStatus"/>
      <el-table-column label="检查详情" prop="checkResult" show-overflow-tooltip/>
      <el-table-column label="备注" prop="remark" show-overflow-tooltip/>
      <el-table-column label="检查日期" prop="checkDate"/>

      <el-table-column v-if="userStore.user.role !== 1" label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button type="warning" icon="Edit" size="small"
                     @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="你确定要删除该检查记录吗？" @confirm="handleDelete(scope.row.id)">
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
import {deleteDormitoryCleanlinessCheckByIdApi} from "@/api/dormitory.js";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['cleanList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (cleanId) => {
  const res = await deleteDormitoryCleanlinessCheckByIdApi(cleanId)
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