<template>
  <el-card class="list-table">
    <el-table :data="healthList" max-height="550" style="width: 100%">
      <el-table-column label="学生名称" prop="studentName"/>
      <el-table-column label="健康状况" prop="healthStatus"/>
      <el-table-column label="体重" prop="weight">
        <template #default="scope">
          {{scope.row.weight}}kg
        </template>
      </el-table-column>
      <el-table-column label="身高" prop="height">
        <template #default="scope">
          {{scope.row.height}}米
        </template>
      </el-table-column>
      <el-table-column label="血压" prop="bloodPressure">
        <template #default="scope">
          {{scope.row.weight}}kpa
        </template>
      </el-table-column>
      <el-table-column label="疫苗接种" prop="vaccinationStatus"/>
      <el-table-column label="过敏史" prop="allergies" show-overflow-tooltip/>
      <el-table-column label="慢性病史" prop="chronicConditions" show-overflow-tooltip/>
      <el-table-column label="记录日期" prop="createTime" width="180"/>

      <el-table-column label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button type="warning" icon="Edit" size="small"
                     @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="你确定要删除该健康记录吗？" @confirm="handleDelete(scope.row.id)">
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
import {deleteStudentHealthRecordByIdApi} from "@/api/student.js";
import {ElMessage} from "element-plus";

const props = defineProps(['healthList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (healthId) => {
  const res = await deleteStudentHealthRecordByIdApi(healthId)
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