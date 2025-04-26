<template>
  <el-dialog v-model="pageParam.statusDialogVisible" title="学籍信息" width="600">
    <el-form :model="userForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="fullName" label="学生姓名">
        <el-input v-model="userForm.fullName" placeholder="请输入学生姓名" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="ethnicity" label="民族">
        <el-input v-model="userForm.ethnicity" placeholder="请输入民族" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="nativePlace" label="籍贯">
        <el-input v-model="userForm.nativePlace" placeholder="请输入籍贯" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="idCard" label="身份证">
        <el-input v-model="userForm.idCard" placeholder="请输入身份证" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="enrollmentYear" label="入学年份">
        <el-date-picker
            v-model="userForm.enrollmentYear"
            type="datetime"
            placeholder="Pick a Date"
            format="YYYY/MM/DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item prop="state" label="学籍状态">
        <el-radio-group v-model="userForm.state">
          <el-radio value="在读">在读</el-radio>
          <el-radio value="休学">休学</el-radio>
          <el-radio value="退学">退学</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="pageParam.statusDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit(formRef)">提交</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {reactive, ref, watch} from "vue";
import {ElMessage} from "element-plus";
import {addStudentStatusApi, editStudentStatusByIdApi} from "@/api/student.js";

const props = defineProps(['pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  studentId: [requiredRule],
  fullName: [requiredRule],
  ethnicity: [requiredRule],
  nativePlace: [requiredRule],
  idCard: [requiredRule],
  enrollmentYear: [requiredRule],
  state: [requiredRule]
};

// 表单
const userForm = reactive({
  id: '',
  studentId: '',
  fullName: '',
  ethnicity: '',
  nativePlace: '',
  idCard: '',
  enrollmentYear: '',
  state: '',
})

const resetUserForm = (user = {}) => {
  userForm.id = user.id || '';
  userForm.studentId = user.studentId || '';
  userForm.fullName = user.fullName;
  userForm.ethnicity = user.ethnicity || '';
  userForm.nativePlace = user.nativePlace || '';
  userForm.idCard = user.idCard || '';
  userForm.enrollmentYear = user.enrollmentYear || '';
  userForm.state = user.state || '';
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.statusDialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetUserForm();
    } else {
      resetUserForm(props.pageParam.studentStatus);
    }
  }
});

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        userForm.studentId = props.pageParam.row.id
        const res = await addStudentStatusApi(userForm)
        if (res.code === 200) {
          ElMessage.success("添加学籍信息成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        delete userForm.enrollmentYear
        const res = await editStudentStatusByIdApi(userForm)
        if (res.code === 200) {
          ElMessage.success("更新学籍信息成功！")
          emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
        }
      }
      props.pageParam.statusDialogVisible = false
    }
  })
}
</script>

<style scoped>

</style>