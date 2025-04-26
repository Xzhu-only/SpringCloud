<template>
  <el-card style="margin-top: -20px;height: 100%">
    <el-row>
      <el-col :span="7"></el-col>
      <el-col :span="10">
        <h2 style="text-align: center;">假期留校申请填表</h2>
        <el-form :model="stayForm" ref="formRef" :rules="rules" label-width="auto">
          <el-form-item prop="dormitoryId" label="留校宿舍">
            <el-select v-model="stayForm.dormitoryId" placeholder="请选择宿舍" style="width: 100%">
              <el-option v-for="item in dormitoryList" :key="item.id" :label="item.buildingName + '-' + item.dormitoryName" :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item prop="stayPeriodStart" label="留校开始日期">
            <el-date-picker
                v-model="stayForm.stayPeriodStart"
                type="datetime"
                placeholder="Pick a Date"
                format="YYYY/MM/DD HH:mm:ss"
            />
          </el-form-item>
          <el-form-item prop="stayPeriodEnd" label="留校结束日期">
            <el-date-picker
                v-model="stayForm.stayPeriodEnd"
                type="datetime"
                placeholder="Pick a Date"
                format="YYYY/MM/DD HH:mm:ss"
            />
          </el-form-item>
          <el-form-item prop="reason" label="留校原因" style="width: 100%">
            <el-input v-model="stayForm.reason" type="textarea" :rows="4" placeholder="请输入留校原因" autocomplete="off" />
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
import {addDormitoryStayRequestApi, getDormitoryListApi} from "@/api/dormitory.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  studentId: [requiredRule],
  dormitoryId: [requiredRule],
  stayPeriodStart: [requiredRule],
  stayPeriodEnd: [requiredRule],
  reason: [requiredRule],
  approvalStatus: [requiredRule],
};

// 表单
const stayForm = reactive({
  studentId: userStore.user.id,
  dormitoryId: '',
  stayPeriodStart: '',
  stayPeriodEnd: '',
  reason: '',
  approvalStatus: '待审批',
})

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      const res = await addDormitoryStayRequestApi(stayForm)
      if (res.code === 200) {
        ElMessage.success("留校申请提交成功！")
        stayForm.studentId = userStore.user.id
        stayForm.dormitoryId = ''
        stayForm.stayPeriodStart = ''
        stayForm.stayPeriodEnd = ''
        stayForm.reason = ''
        stayForm.approvalStatus = '待审批'
      }
    }
  })
}

const dormitoryList = ref([])
const getDormitoryList = async () => {
  const res = await getDormitoryListApi()
  if (res.code === 200) {
    dormitoryList.value = res.data
  } else {
    dormitoryList.value = []
  }
}

onMounted(() => {
  getDormitoryList()
})
</script>

<style scoped>

</style>