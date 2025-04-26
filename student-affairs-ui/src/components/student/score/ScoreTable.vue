<template>
  <el-card class="list-table">
    <el-table :data="gradeList" max-height="550" style="width: 100%">
      <el-table-column label="课程名" prop="course.courseName"/>
      <el-table-column label="课程编号" prop="course.courseCode"/>
      <el-table-column label="课程学分" prop="course.credit"/>
      <el-table-column label="选课学生" prop="studentName"/>
      <el-table-column label="学期" prop="semester" width="200" show-overflow-tooltip/>
      <el-table-column label="选课日期" prop="selectionDate" width="200"/>
      <el-table-column label="选课成绩" prop="courseGrades.grade" width="100">
        <template #default="scope">
         {{scope.row.courseGrades !== null ? scope.row.courseGrades.grade + '分' : '成绩未录入'}}
        </template>
      </el-table-column>
      <el-table-column label="成绩录入日期" prop="courseGrades.gradeDate" width="200"  >
        <template #default="scope">
          {{scope.row.courseGrades !== null ? scope.row.courseGrades.gradeDate : '成绩未录入'}}
        </template>
      </el-table-column>

      <el-table-column v-if="userStore.user.role !== 1" label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button :disabled="scope.row.courseGrades" type="primary" icon="Plus" size="small"
                     @click="handleEdit(scope.row)">成绩录入</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['gradeList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
</script>

<style scoped>
.list-table {
  height: 100%;
}
</style>