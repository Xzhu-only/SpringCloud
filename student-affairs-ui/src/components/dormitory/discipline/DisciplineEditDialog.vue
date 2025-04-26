<template>
  <el-dialog v-model="pageParam.dialogVisible" :title="pageParam.addOrEditFlag ? '添加违纪记录' : '编辑违纪记录'" width="600">
    <el-form :model="recordForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="dormitoryId" label="宿舍">
        <el-select v-model="recordForm.dormitoryId" placeholder="请选择宿舍" style="width: 200px">
          <el-option v-for="item in dormitoryList" :key="item.id" :label="item.buildingName + '-' + item.dormitoryName" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item prop="incidentType" label="违纪类型">
        <el-select v-model="recordForm.incidentType" placeholder="请选择违纪类型" style="width: 200px">
          <el-option label="吵闹" value="吵闹"/>
          <el-option label="晚归" value="晚归"/>
          <el-option label="打架" value="打架"/>
          <el-option label="串寝" value="串寝"/>
        </el-select>
      </el-form-item>
      <el-form-item prop="incidentDetails" label="违纪详情">
        <el-input v-model="recordForm.incidentDetails" type="textarea" :rows="4" placeholder="请输入违纪详情" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="punishment" label="处罚措施">
        <el-input v-model="recordForm.punishment" type="textarea" :rows="4" placeholder="处罚措施" autocomplete="off" />
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
  addDormitoryDisciplineRecordApi,
  editDormitoryDisciplineRecordByIdApi
} from "@/api/dormitory.js";

const userStore = useUserStore()
const props = defineProps(['dormitoryList', 'pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  dormitoryId: [requiredRule],
  incidentType: [requiredRule],
  incidentDetails: [requiredRule],
  punishment: [requiredRule],
};

// 表单
const recordForm = reactive({
  id: '',
  dormitoryId: '',
  incidentType: '',
  incidentDetails: '',
  punishment: '',
})

const resetRecordForm = (record = {}) => {
  recordForm.id = record.id || '';
  recordForm.dormitoryId = record.dormitoryId || '';
  recordForm.incidentType = record.incidentType || '';
  recordForm.incidentDetails = record.incidentDetails || '';
  recordForm.punishment = record.punishment || '';
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.dialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetRecordForm();
    } else {
      resetRecordForm(props.pageParam.row);
    }
  }
});

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        const res = await addDormitoryDisciplineRecordApi(recordForm)
        if (res.code === 200) {
          ElMessage.success("添加违纪记录成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        const res = await editDormitoryDisciplineRecordByIdApi(recordForm)
        if (res.code === 200) {
          ElMessage.success("更新违纪记录成功！")
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