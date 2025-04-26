<template>
  <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false" background-color="rgb(82, 83.5, 86.5)" router>
    <el-menu-item></el-menu-item>
    <div class="toolbar">
      <span style="color: white;font-size: 15px">{{userStore.user.username}}({{userStore.user.role === 0 ? '管理员' :
          userStore.user.role === 1 ? '学生' : '教师'}})</span>
      <el-dropdown style="margin-left: 8px;margin-top: 3px">
        <span><el-avatar :size="45" :src="userStore.user.avatar" /></span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item v-if="userStore.user" @click="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-menu>
</template>

<script setup>
import {useUserStore} from "@/stores/user.js";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

const router = useRouter()
const userStore = useUserStore()

//退出登录
const logout = () => {
  userStore.deleteUser();
  localStorage.removeItem("token")
  ElMessage.success("退出成功！")
  router.push('/login')
}

const toInfo = () => {
  router.push('/info')
}
</script>

<style scoped>
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}

.el-menu-demo {
  height: 100%;
  margin-left: -20px;
  margin-right: -20px;
}

.toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
  margin-right: 30px;
}
</style>