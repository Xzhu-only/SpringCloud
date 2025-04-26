<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <el-form :inline="true" ref="formRef" :rules="rules" label-width="auto"
               :model="registerForm">
        <el-form-item >
          <h1>用户注册</h1>
        </el-form-item>
        <el-form-item prop="username" label="用户名" style="width: 100%">
          <el-input :prefix-icon="User" v-model.trim="registerForm.username" placeholder="请输入用户名"/>
        </el-form-item>
        <el-form-item prop="password" label="密码" style="width: 100%">
          <el-input type="password" :prefix-icon="Lock" v-model.trim="registerForm.password" placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item prop="email" label="邮箱" style="width: 100%">
          <el-input :prefix-icon="Message" v-model.trim="registerForm.email" placeholder="请输入邮箱"/>
        </el-form-item>
        <el-form-item prop="role" label="角色" style="width: 100%">
          <el-radio-group v-model="registerForm.role">
            <el-radio :value="1">学生</el-radio>
            <el-radio :value="2">老师</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="avatar" label="头像">
          <el-upload
              action="/api/file/upload/image"
              list-type="picture-card"
              :on-success="handleUploadSuccess"
              :on-error="handleUploadError"
              :file-list="fileList"
              :auto-upload="true"
              :limit="1">
            <el-icon class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>

        <el-form-item style="width: 100%">
          <el-button type="primary" @click="onSubmit(formRef)" style="width: 100%">注册提交</el-button>
        </el-form-item><br/>
        <el-form-item>
          <el-link @click="goToLogin" style="float: left;">已经有账号了？去登录</el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>


<script setup>
import {ref, reactive } from 'vue'
import {ElMessage} from 'element-plus'
import {useRouter} from 'vue-router'
import {Lock, User, Message} from '@element-plus/icons-vue'
import {userRegisterApi} from "@/api/user.js";

const router = useRouter();

// 注册验证规则
const formRef = ref()
const rules = {
  username: [
    { required: true, message: '请输入您的用户名', trigger: 'blur' },
    { min: 2, max: 15, message: '用户名长度必须介于2到15个字符之间', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入您的密码', trigger: 'blur' },
    { min: 6, max: 15, message: '密码长度必须在6到15个字符之间', trigger: 'blur' },
  ],
  email: [
    { required: true, message: '请输入您的电子邮件', trigger: 'blur' },
    {
      pattern: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
      message: '请输入有效的电子邮件地址',
      trigger: 'blur'
    },
  ],
  role: [
    { required: true, message: '请选择注册角色', trigger: 'blur' },
  ],
  avatar: [
    { required: true, message: '请上传您的注册头像', trigger: 'blur' },
  ],
}

// 图片上传
const fileList = ref([]);
// 上传成功响应
const handleUploadSuccess = (response, file, fileList) => {
  registerForm.avatar = response.data
  ElMessage.success('图片上传成功');
};
// 上传失败响应
const handleUploadError = (err) => {
  ElMessage.error('图像上传失败');
};

// 注册表单
const registerForm = reactive({
  username: '',
  password: '',
  email: '',
  avatar: '',
  role: '',
})

// 注册按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      userRegisterApi(registerForm).then(res => {
        ElMessage.success("注册成功，一秒后跳转到登录页面")
        fileList.value = []
        // 一秒定时器
        setTimeout(() => {
          router.push("/login");
        }, 1000);
      })
    }
  })
}

// 跳转到登录页面
const goToLogin = () => {
  router.push("/login");
};
</script>

<style scoped lang="scss">
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background: url("@/assets/login.png");
  }

  .form {
    display: flex;
    justify-content: center;
    flex-direction: column;

    button {
      width: 100%;
    }
  }
}
</style>
