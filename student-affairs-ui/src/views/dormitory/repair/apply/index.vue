<template>
  <el-card style="margin-top: -20px;height: 100%">
    <el-row>
      <el-col :span="7"></el-col>
      <el-col :span="10">
        <h2 style="text-align: center;">宿舍报修申请填表</h2>
        <el-form :model="repairForm" ref="formRef" :rules="rules" label-width="auto">
          <el-form-item prop="dormitoryId" label="宿舍">
            <el-select v-model="repairForm.dormitoryId" placeholder="请选择宿舍" style="width: 100%">
              <el-option v-for="item in dormitoryList" :key="item.id" :label="item.buildingName + '-' + item.dormitoryName" :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item prop="issueType" label="问题类型">
            <el-select v-model="repairForm.issueType" placeholder="请选择问题类型" style="width: 100%">
              <el-option label="水管漏水" value="水管漏水"/>
              <el-option label="电器故障" value="电器故障"/>
              <el-option label="灯泡故障" value="灯泡故障"/>
              <el-option label="门锁故障" value="门锁故障"/>
            </el-select>
          </el-form-item>
          <el-form-item prop="issueDescription" label="问题描述" style="width: 100%">
            <el-input v-model="repairForm.issueDescription" type="textarea" :rows="4" placeholder="请输入报修问题描述" autocomplete="off" />
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
import {addDormitoryRepairRequestApi, getDormitoryListApi} from "@/api/dormitory.js";
import {ElMessage} from "element-plus";

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  dormitoryId: [requiredRule],
  issueType: [requiredRule],
  issueDescription: [requiredRule],
  repairStatus: [requiredRule],
};

// 表单
const repairForm = reactive({
  dormitoryId: '',
  issueType: '',
  issueDescription: '',
  repairStatus: '待处理',
})

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      const res = await addDormitoryRepairRequestApi(repairForm)
      if (res.code === 200) {
        ElMessage.success("报修申请成功！")
        repairForm.dormitoryId = ''
        repairForm.issueType = ''
        repairForm.issueDescription = ''
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