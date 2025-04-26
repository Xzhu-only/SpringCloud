<template>
  <el-card class="list-table">
    <h3 style="margin-top: -6px">我的新生报道登记记录：</h3>
    <el-table :data="registrationList" max-height="550" style="width: 100%">
      <el-table-column label="新生姓名" prop="studentName"/>
      <el-table-column label="新生学号" prop="studentId"/>
      <el-table-column label="性别" prop="gender"/>
      <el-table-column label="手机号" prop="phoneNumber"/>
      <el-table-column label="邮箱" prop="email"/>
      <el-table-column label="状态" prop="state">
        <template #default="scope">
          <el-tag v-if="scope.row.state === '未签到'" type="warning">未签到</el-tag>
          <el-tag v-if="scope.row.state === '已签到'" type="success">已签到</el-tag>
          <el-tag v-if="scope.row.state === '已取消'" type="info">已取消</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" prop="remark"/>
      <el-table-column label="登记时间" prop="createTime"/>

      <el-table-column label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button v-if="scope.row.state === '未签到'" type="success" icon="Select" size="small"
                     @click="handleEdit(scope.row, '已签到')">签到</el-button>
          <el-button v-if="scope.row.state === '未签到'" type="info" icon="Close" size="small"
                     @click="handleEdit(scope.row, '已取消')">取消</el-button>
          <el-popconfirm v-if="scope.row.state !== '未签到'" title="你确定要删除记录吗？" @confirm="handleDelete(scope.row.id)">
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
import {deleteStudentRegistrationByIdApi, editStudentRegistrationByIdApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['registrationList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = async (row, state) => {
  row.state = state
  const res = await editStudentRegistrationByIdApi(row)
  if (res.code === 200) {
    ElMessage.success('操作成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
//处理删除按钮响应
const handleDelete = async (registrationId) => {
  const res = await deleteStudentRegistrationByIdApi(registrationId)
  if (res.code === 200) {
    ElMessage.success('删除成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
</script>

<style scoped>
.list-table {
  margin-top: -20px;
  height: 100%;
}
</style>