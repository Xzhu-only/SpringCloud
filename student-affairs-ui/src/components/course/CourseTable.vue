<template>
  <el-card class="list-table">
    <el-table :data="courseList" max-height="550" style="width: 100%">
      <el-table-column label="课程名" prop="courseName"/>
      <el-table-column label="课程编号" prop="courseCode"/>
      <el-table-column label="课程学分" prop="credit"/>
      <el-table-column label="课程描述" prop="description" show-overflow-tooltip/>
      <el-table-column label="授课老师" prop="teacherName"/>
      <el-table-column label="创建时间" prop="createTime"/>

      <el-table-column label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button v-if="userStore.user.role !== 1" type="warning" icon="Edit" size="small"
                     @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm v-if="userStore.user.role !== 1" title="你确定要删除该课程吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" icon="Delete" size="small" >删除</el-button>
            </template>
          </el-popconfirm>

          <el-button v-if="userStore.user.role === 1" type="success" icon="Select" size="small"
                     @click="handleSelect(scope.row)">选课</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

  <el-dialog v-model="semesterDialogVisible" title="学生选课" width="600">
    <el-form :model="selectionForm" label-width="auto">
      <el-form-item prop="semester" label="学期选择">
        <el-select v-model="selectionForm.semester" placeholder="请选择课程学期" style="width: 240px">
          <el-option label="2024-2025第一学期" value="2024-2025第一学期"/>
          <el-option label="2024-2025第二学期" value="2024-2025第二学期"/>
          <el-option label="2025-2026第一学期" value="2025-2026第一学期"/>
          <el-option label="2025-2026第二学期" value="2025-2026第二学期"/>
        </el-select>
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="semesterDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {addCourseSelectionApi, deleteCourseByIdApi} from "@/api/course.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";
import {reactive, ref} from "vue";

const userStore = useUserStore()
const props = defineProps(['courseList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (courseId) => {
  const res = await deleteCourseByIdApi(courseId)
  if (res.code === 200) {
    ElMessage.success('删除成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}

const semesterDialogVisible = ref(false)
const selectionForm = reactive({
  studentId: '',
  courseId: '',
  semester: '',
})
//处理选课按钮响应
const handleSelect = (row) => {
  selectionForm.studentId = userStore.user.id
  selectionForm.courseId = row.id
  selectionForm.semester = ''
  semesterDialogVisible.value = true
}
const onSubmit = async () => {
  if (selectionForm.semester === undefined || selectionForm.semester === '') {
    ElMessage.error("选课学期还未选择！");
    return;
  }
  const res = await addCourseSelectionApi(selectionForm)
  if (res.code === 200) {
    ElMessage.success("选课成功！");
    semesterDialogVisible.value = false
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
</script>

<style scoped>
.list-table {
  height: 100%;
}
</style>