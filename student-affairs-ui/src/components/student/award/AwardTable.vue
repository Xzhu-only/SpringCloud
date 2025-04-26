<template>
  <el-card class="list-table">
    <el-table :data="awardList" max-height="550" style="width: 100%">
      <el-table-column label="奖惩学生" prop="studentName"/>
      <el-table-column label="奖惩类型" prop="type">
        <template #default="scope">
          <el-tag v-if="scope.row.type === '奖励'" type="success">奖励</el-tag>
          <el-tag v-if="scope.row.type === '惩罚'" type="danger">惩罚</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="奖惩原因" prop="reason" show-overflow-tooltip/>
      <el-table-column label="详情" prop="details" show-overflow-tooltip/>
      <el-table-column label="奖惩时间" prop="createTime"/>

      <el-table-column label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button v-if="userStore.user.role !== 1" type="warning" icon="Edit" size="small"
                     @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm v-if="userStore.user.role !== 1" title="你确定要删除该奖惩记录吗？" @confirm="handleDelete(scope.row.id)">
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
import {deleteStudentAwardPunishmentByIdApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['awardList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (awardId) => {
  const res = await deleteStudentAwardPunishmentByIdApi(awardId)
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