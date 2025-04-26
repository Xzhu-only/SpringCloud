<template>
  <el-card class="list-table">
    <h3 style="margin-top: -6px">我的宿舍：</h3>
    <el-table :data="dormitoryList" max-height="550" style="width: 100%">
      <el-table-column label="学生名称" prop="studentName"/>
      <el-table-column label="宿舍名" prop="dormitory.dormitoryName"/>
      <el-table-column label="宿舍楼" prop="dormitory.buildingName"/>
      <el-table-column label="最大人数" prop="dormitory.capacity">
        <template #default="scope">
          {{scope.row.dormitory.capacity}}人
        </template>
      </el-table-column>
      <el-table-column label="已入住人数" prop="dormitory.currentOccupancy">
        <template #default="scope">
          {{scope.row.dormitory.currentOccupancy}}人
        </template>
      </el-table-column>
      <el-table-column label="入住时间" prop="moveInDate"/>
      <el-table-column label="退宿时间" prop="moveOutDate"/>

      <el-table-column label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-popconfirm title="你确定要退宿吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" icon="Delete" size="small" >退宿</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
import {deleteStudentDormitoryByIdApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['dormitoryList', 'pageParam'])
const emit = defineEmits()

//处理删除按钮响应
const handleDelete = async (dormitoryId) => {
  const res = await deleteStudentDormitoryByIdApi(dormitoryId)
  if (res.code === 200) {
    ElMessage.success('退宿成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
</script>

<style scoped>
.list-table {
  height: 100%;
}
</style>