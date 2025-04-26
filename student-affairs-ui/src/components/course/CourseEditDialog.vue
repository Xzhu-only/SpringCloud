<template>
  <el-dialog v-model="pageParam.dialogVisible" :title="pageParam.addOrEditFlag ? '添加课程' : '编辑课程'" width="600">
    <el-form :model="courseForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="courseName" label="课程名">
        <el-input v-model="courseForm.courseName" placeholder="请输入课程名" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="courseCode" label="课程编号">
        <el-input v-model="courseForm.courseCode" placeholder="请输入课程编号" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="credit" label="课程学分">
        <el-input-number v-model="courseForm.credit" :min="1" :max="10" size="small"/>
      </el-form-item>
      <el-form-item v-if="userStore.user.role === 0" prop="teacherId" label="授课老师">
        <el-select v-model="courseForm.teacherId" placeholder="请选择授课老师" style="width: 240px">
          <el-option v-for="item in teacherList" :key="item.id" :label="item.username" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item prop="description" label="课程描述">
        <el-input v-model="courseForm.description" type="textarea" :rows="4" placeholder="请输入课程描述" autocomplete="off" />
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="pageParam.dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit(formRef)">提交</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {onMounted, reactive, ref, watch} from "vue";
import {addCourseApi, editCourseByIdApi} from "@/api/course.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";
import {getUserListByRoleApi} from "@/api/user.js";

const userStore = useUserStore()
const props = defineProps(['pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  courseName: [requiredRule],
  credit: [requiredRule],
  courseCode: [requiredRule],
  description: [requiredRule],
};

// 表单
const courseForm = reactive({
  id: '',
  courseName: '',
  credit: '',
  courseCode: '',
  description: '',
  teacherId: '',
})

const resetCourseForm = (course = {}) => {
  courseForm.id = course.id || '';
  courseForm.courseName = course.courseName || '';
  courseForm.credit = course.credit;
  courseForm.courseCode = course.courseCode || '';
  courseForm.description = course.description || '';
  courseForm.teacherId = course.teacherId || '';
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.dialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetCourseForm();
    } else {
      resetCourseForm(props.pageParam.row);
    }
  }
});

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        if (userStore.user.role === 2) {
          courseForm.teacherId = userStore.user.id
        }
        const res = await addCourseApi(courseForm)
        if (res.code === 200) {
          ElMessage.success("添加课程成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        const res = await editCourseByIdApi(courseForm)
        if (res.code === 200) {
          ElMessage.success("更新课程成功！")
          emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
        }
      }
      props.pageParam.dialogVisible = false
    }
  })
}

const teacherList = ref([])
const getTeacherList = async () => {
  const res = await getUserListByRoleApi(2)
  if (res.code === 200) {
    teacherList.value = res.data
  } else {
    teacherList.value = []
  }
}

onMounted(() => {
  getTeacherList()
})
</script>

<style scoped>

</style>