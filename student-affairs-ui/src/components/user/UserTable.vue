<template>
  <el-card class="list-table">
    <el-table :data="userList" max-height="550" style="width: 100%">
      <el-table-column label="用户名" prop="username"/>
      <el-table-column label="头像" prop="avatar">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-avatar :size="45" :src="scope.row.avatar" />
          </div>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" prop="email"/>
      <el-table-column label="角色" prop="role">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-tag type="primary" v-if="scope.row.role === 0">管理员</el-tag>
            <el-tag type="success" v-else-if="scope.row.role === 1">学生</el-tag>
            <el-tag type="warning" v-else-if="scope.row.role === 2">教师</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="state">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-tag type="primary" v-if="scope.row.state === 0">正常</el-tag>
            <el-tag type="danger" v-else-if="scope.row.state === 1">封禁中</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="注册时间" prop="createTime"></el-table-column>

      <el-table-column label="操作" align="left" fixed="right" width="270">
        <template #default="scope">
          <el-button v-if="scope.row.username !== 'admin'" type="warning" icon="Edit" size="small"
                     @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm v-if="scope.row.username !== 'admin'" title="你确定要删除该用户吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" icon="Delete" size="small" >删除</el-button>
            </template>
          </el-popconfirm>
          <el-button v-if="scope.row.role === 1" type="primary" icon="Select" size="small"
                     @click="handleStatus(scope.row)">学籍调动</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
import {deleteUserByIdApi} from "@/api/user.js";
import {ElMessage} from "element-plus";
import {getStudentStatusApi} from "@/api/student.js";

const props = defineProps(['userList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (userId) => {
  const res = await deleteUserByIdApi(userId)
  if (res.code === 200) {
    ElMessage.success('删除成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
//学籍调用按钮响应
const handleStatus = async (row) => {
  const res = await getStudentStatusApi(row.id)
  if (res.code === 200) {
    props.pageParam.statusDialogVisible = true
    props.pageParam.row = row
    if (!res.data) {
      props.pageParam.addOrEditFlag = true
    } else {
      props.pageParam.addOrEditFlag = false
    }
    props.pageParam.studentStatus = res.data
  }
}
</script>

<style scoped>
.list-table {
  height: 100%;
}
</style>