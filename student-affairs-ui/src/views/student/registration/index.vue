<template>
  <el-card style="margin-top: -20px;height: 100%">
    <el-row>
      <el-col :span="7"></el-col>
      <el-col :span="10">
        <h2 style="text-align: center;">新生报道登记表</h2>
        <el-form :model="registrationForm" ref="formRef" :rules="rules" label-width="auto">
          <el-form-item prop="studentName" label="新生姓名" style="width: 100%">
            <el-input v-model="registrationForm.studentName" placeholder="请输入新生姓名" autocomplete="off" />
          </el-form-item>
          <el-form-item prop="studentId" label="新生学号" style="width: 100%">
            <el-input v-model="registrationForm.studentId" placeholder="请输入新生学号" autocomplete="off" />
          </el-form-item>
          <el-form-item prop="gender" label="性别" style="width: 100%">
            <el-radio-group v-model="registrationForm.gender">
              <el-radio value="男">男</el-radio>
              <el-radio value="女">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="phoneNumber" label="手机号" style="width: 100%">
            <el-input v-model="registrationForm.phoneNumber" placeholder="请输入手机号" autocomplete="off" />
          </el-form-item>
          <el-form-item prop="email" label="邮箱" style="width: 100%">
            <el-input v-model="registrationForm.email" placeholder="请输入邮箱" autocomplete="off" />
          </el-form-item>
          <el-form-item prop="remark" label="备注" style="width: 100%">
            <el-input v-model="registrationForm.remark" type="textarea" :rows="4" placeholder="备注（可选）" autocomplete="off" />
          </el-form-item>

          <el-form-item style="width: 100%">
            <el-button type="primary" @click="onSubmit(formRef)" style="width: 100%">提交</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="7"></el-col>
    </el-row>
  </el-card>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {addStudentRegistrationApi} from "@/api/student.js";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
// 验证规则
const formRef = ref()
const requiredRule = {required: true, message: '该字段为必填项', trigger: 'blur'};
const rules = {
  studentName: [requiredRule],
  studentId: [requiredRule],
  gender: [requiredRule],
  phoneNumber: [requiredRule],
  email: [requiredRule],
  state: [requiredRule],
  userId: [requiredRule],
};

// 表单
const registrationForm = reactive({
  studentName: '',
  studentId: '',
  gender: '',
  phoneNumber: '',
  email: '',
  state: '未签到',
  userId: userStore.user.id,
  remark: '',
})

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      const res = await addStudentRegistrationApi(registrationForm)
      if (res.code === 200) {
        ElMessage.success("登记成功！")
        registrationForm.studentName = ''
        registrationForm.studentId = ''
        registrationForm.gender = ''
        registrationForm.phoneNumber = ''
        registrationForm.email = ''
        registrationForm.remark = ''
      }
    }
  })
}
</script>

<style scoped>

</style>