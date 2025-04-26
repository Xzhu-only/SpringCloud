<template>
  <el-dialog v-model="pageParam.dialogVisible" :title="pageParam.addOrEditFlag ? '添加健康记录' : '编辑健康记录'" width="600">
    <el-form :model="healthForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item v-if="userStore.user.role !== 1" prop="studentId" label="学生">
        <el-select v-model="healthForm.studentId" placeholder="请选择记录的学生" style="width: 240px">
          <el-option v-for="item in studentList" :key="item.id" :label="item.username" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item prop="healthStatus" label="健康状况">
        <el-radio-group v-model="healthForm.healthStatus">
          <el-radio value="优秀">优秀</el-radio>
          <el-radio value="良好">良好</el-radio>
          <el-radio value="不良">不良</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="weight" label="体重(kg)">
        <el-input-number v-model="healthForm.weight" :precision="2" :step="0.1" :min="0" />
      </el-form-item>
      <el-form-item prop="height" label="身高(米)">
        <el-input-number v-model="healthForm.height" :precision="2" :step="0.1" :min="0" />
      </el-form-item>
      <el-form-item prop="bloodPressure" label="血压(kpa)">
        <el-input-number v-model="healthForm.bloodPressure" :precision="2" :step="0.1" :min="0" />
      </el-form-item>
      <el-form-item prop="vaccinationStatus" label="疫苗接种">
        <el-radio-group v-model="healthForm.vaccinationStatus">
          <el-radio value="已接种">已接种</el-radio>
          <el-radio value="未接种">未接种</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="allergies" label="过敏史">
        <el-input v-model="healthForm.allergies" type="textarea" :rows="4" placeholder="请输入过敏史，没有填无" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="chronicConditions" label="慢性病史">
        <el-input v-model="healthForm.chronicConditions" type="textarea" :rows="4" placeholder="请输入慢性病史，没有填无" autocomplete="off" />
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
import {
  addStudentHealthRecordApi,
  editStudentHealthRecordByIdApi
} from "@/api/student.js";

const userStore = useUserStore()
const props = defineProps(['studentList', 'pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  studentId: [requiredRule],
  healthStatus: [requiredRule],
  weight: [requiredRule],
  height: [requiredRule],
  bloodPressure: [requiredRule],
  vaccinationStatus: [requiredRule],
  allergies: [requiredRule],
  chronicConditions: [requiredRule],
};

// 表单
const healthForm = reactive({
  id: '',
  studentId: '',
  healthStatus: '',
  weight: '',
  height: '',
  bloodPressure: '',
  vaccinationStatus: '',
  allergies: '',
  chronicConditions: '',
})

const resetHealthCardForm = (health = {}) => {
  healthForm.id = health.id || '';
  healthForm.studentId = health.studentId || '';
  healthForm.healthStatus = health.healthStatus || '';
  healthForm.weight = health.weight || '';
  healthForm.height = health.height || '';
  healthForm.bloodPressure = health.bloodPressure || '';
  healthForm.vaccinationStatus = health.vaccinationStatus || '';
  healthForm.allergies = health.allergies || '';
  healthForm.chronicConditions = health.chronicConditions || '';
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.dialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetHealthCardForm();
    } else {
      resetHealthCardForm(props.pageParam.row);
    }
  }
});

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        if (userStore.user.role === 1) {
          healthForm.studentId = userStore.user.id
        }
        const res = await addStudentHealthRecordApi(healthForm)
        if (res.code === 200) {
          ElMessage.success("添加健康记录成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        const res = await editStudentHealthRecordByIdApi(healthForm)
        if (res.code === 200) {
          ElMessage.success("更新健康记录成功！")
          emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
        }
      }
      props.pageParam.dialogVisible = false
    }
  })
}
</script>

<style scoped>

</style>