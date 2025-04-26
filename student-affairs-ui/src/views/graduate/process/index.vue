<template>
  <h2 style="text-align: center">毕业流程</h2>
  <el-steps :active="active" align-center finish-status="success">
    <el-step title="校园卡注销">
      <template #description>
        <el-popconfirm title="你确定要进行注销操作吗？" @confirm="deleteCampusCard">
          <template #reference>
            <el-button :disabled="active !== 0" plain size="small" type="warning">校园卡注销</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-step>
    <el-step title="学生证注销">
      <template #description>
        <el-popconfirm title="你确定要进行注销操作吗？" @confirm="deleteStudentCard">
          <template #reference>
            <el-button :disabled="active !== 1" plain size="small" type="success">学生证注销</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-step>
    <el-step title="宿舍退宿">
      <template #description>
        <el-popconfirm title="你确定要进行退宿操作吗？" @confirm="deleteStudentDormitory">
          <template #reference>
            <el-button :disabled="active !== 2" plain size="small" type="primary">退宿</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-step>
    <el-step title="毕业手续办理">
      <template #description>
        <el-popconfirm title="你确定要进行办理操作吗？" @confirm="handleGraduate">
          <template #reference>
            <el-button :disabled="active !== 3" plain size="small" type="warning">办理</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-step>
  </el-steps>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {deleteUserByIdApi, getGraduateActive} from "@/api/user.js";
import {deleteCampusCardByIdApi, deleteStudentCardByIdApi, deleteStudentDormitoryByIdApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";
import {useRouter} from "vue-router";

const router = useRouter()
const userStore = useUserStore()
const active = ref(0)
const id = ref(0)

//校园卡注销
const deleteCampusCard = async () => {
  const res = await deleteCampusCardByIdApi(id.value)
  if (res.code === 200) {
    ElMessage.success('校园卡注销成功！')
    await getActive()
  }
}
//学生证注销
const deleteStudentCard = async () => {
  const res = await deleteStudentCardByIdApi(id.value)
  if (res.code === 200) {
    ElMessage.success('学生证注销成功！')
    await getActive()
  }
}
//宿舍退宿
const deleteStudentDormitory = async () => {
  const res = await deleteStudentDormitoryByIdApi(id.value)
  if (res.code === 200) {
    ElMessage.success('退宿成功！')
    await getActive()
  }
}
//毕业手续办理
const handleGraduate = async () => {
  const res = await deleteUserByIdApi(userStore.user.id)
  if (res.code === 200) {
    ElMessage.success('办理成功，即将退出系统！')
    await getActive()
    // 一秒定时器
    setTimeout(() => {
      router.push("/login");
    }, 1000);
  }
}
//查询当前毕业流程执行步骤
const getActive = async () => {
  const res = await getGraduateActive()
  if (res.code === 200) {
    active.value = res.data
    id.value = res.id
  }
}

onMounted(() => {
  getActive()
})
</script>

<style scoped>

</style>