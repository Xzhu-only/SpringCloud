<template>
  <el-card class="list-table">
    <el-table :data="dormitoryList" max-height="550" style="width: 100%">
      <el-table-column label="宿舍名" prop="dormitoryName"/>
      <el-table-column label="宿舍楼" prop="buildingName"/>
      <el-table-column label="最大人数" prop="capacity">
        <template #default="scope">
          {{scope.row.capacity}}人
        </template>
      </el-table-column>
      <el-table-column label="已入住人数" prop="currentOccupancy">
        <template #default="scope">
          {{scope.row.currentOccupancy}}人
        </template>
      </el-table-column>
      <el-table-column label="负责老师" prop="teacherName"/>
      <el-table-column label="创建时间" prop="createTime"/>

      <el-table-column v-if="userStore.user.role === 0" label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button type="warning" icon="Edit" size="small"
                     @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="你确定要删除该宿舍吗？" @confirm="handleDelete(scope.row.id)">
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
import {deleteDormitoryByIdApi} from "@/api/dormitory.js";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['dormitoryList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (dormitoryId) => {
  const res = await deleteDormitoryByIdApi(dormitoryId)
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