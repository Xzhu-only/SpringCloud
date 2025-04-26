<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <el-form
          ref="formRef"
          :rules="rules"
          label-width="auto"
          :model="loginData"
          style="max-width: 600px">
        <el-form-item >
          <h1>学工服务平台</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" v-model.trim="loginData.username" placeholder="请输入用户名"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" :prefix-icon="Lock" v-model.trim="loginData.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit(formRef)">登录</el-button>
        </el-form-item>
        <el-form-item style="float: right">
          <el-link @click="toRegister" >还没有账号？去注册</el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
import { Lock, User } from '@element-plus/icons-vue';
import {getLoginInfoApi, userLoginApi} from "@/api/user.js";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const router = useRouter();

// 登录校验规则
const formRef = ref();
const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 15, message: '用户名长度必须介于2到15个字符之间', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 15, message: '密码长度必须在6到15个字符之间', trigger: 'blur' },
  ]
};

// 登录表单数据
const loginData = reactive({
  username: '',
  password: '',
});

// 登录提交
const onSubmit =  (formEl) => {
  if (formEl) {
    formEl.validate(async (valid, fields) => {
      if (valid) {
        const res = await userLoginApi(loginData);
        if (res.code === 200) {
          localStorage.setItem("token", res.token);
          ElMessage.success("登录成功")
          //发送请求查询登录信息
          const res1 = await getLoginInfoApi()
          if (res1.code === 200) {
            userStore.setUser(res1.data)
            router.push("/home");
          }
        }
      }
    });
  }
};

// 跳转到注册页面
const toRegister = () => {
  router.push("/register");
};
</script>

<style scoped>
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
