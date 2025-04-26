<template>
  <el-dialog v-model="pageParam.dialogVisible" title="课程成绩录入" width="600">
    <el-form :model="gradeForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="grade" label="成绩">
        <el-input-number v-model="gradeForm.grade" :min="0" :max="150" size="small"/>
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
import {reactive, ref, watch} from "vue";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";
import {addCourseGradesApi} from "@/api/course.js";

const userStore = useUserStore()
const props = defineProps(['pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  selectionId: [requiredRule],
  grade: [requiredRule],
};

// 表单
const gradeForm = reactive({
  selectionId: '',
  grade: '',
})

const resetCourseForm = (grade = {}) => {
  gradeForm.selectionId = grade.id || '';
  gradeForm.grade = '';
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
      const res = await addCourseGradesApi(gradeForm)
      if (res.code === 200) {
        ElMessage.success("添加成绩成功！")
        emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
      }
      props.pageParam.dialogVisible = false
    }
  })
}
</script>

<style scoped>

</style>