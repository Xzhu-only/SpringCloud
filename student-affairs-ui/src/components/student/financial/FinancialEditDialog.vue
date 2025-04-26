<template>
  <el-dialog v-model="pageParam.dialogVisible" :title="pageParam.addOrEditFlag ? '添加资助记录' : '编辑资助记录'" width="600">
    <el-form :model="aidForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="studentId" label="资助学生">
        <el-select v-model="aidForm.studentId" placeholder="请选择资助学生" style="width: 240px">
          <el-option v-for="item in studentList" :key="item.id" :label="item.username" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item prop="aidType" label="资助类型">
        <el-radio-group v-model="aidForm.aidType">
          <el-radio value="奖学金">奖学金</el-radio>
          <el-radio value="助学金">助学金</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="aidAmount" label="资助金额">
        <el-input-number v-model="aidForm.aidAmount" :precision="2" :step="0.5" :min="0" />
      </el-form-item>
      <el-form-item prop="semester" label="资助学期">
        <el-select v-model="aidForm.semester" placeholder="请选择资助学期" style="width: 240px">
          <el-option label="2024-2025第一学期" value="2024-2025第一学期"/>
          <el-option label="2024-2025第二学期" value="2024-2025第二学期"/>
          <el-option label="2025-2026第一学期" value="2025-2026第一学期"/>
          <el-option label="2025-2026第二学期" value="2025-2026第二学期"/>
        </el-select>
      </el-form-item>
      <el-form-item prop="aidSource" label="资助来源">
        <el-input v-model="aidForm.aidSource"  placeholder="请输入资助来源" autocomplete="off" />
      </el-form-item>
      <el-form-item v-if="!pageParam.addOrEditFlag" prop="state" label="资助状态">
        <el-radio-group v-model="aidForm.state">
          <el-radio value="待领取">待领取</el-radio>
          <el-radio value="已领取">已领取</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="remark" label="备注">
        <el-input v-model="aidForm.remark" type="textarea" :rows="4" placeholder="备注（可选）" autocomplete="off" />
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
  addStudentFinancialAidApi, editStudentFinancialAidByIdApi,
} from "@/api/student.js";

const userStore = useUserStore()
const props = defineProps(['studentList', 'pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  studentId: [requiredRule],
  aidType: [requiredRule],
  aidAmount: [requiredRule],
  semester: [requiredRule],
  aidSource: [requiredRule],
  state: [requiredRule],
};

// 表单
const aidForm = reactive({
  id: '',
  studentId: '',
  aidType: '',
  aidAmount: '',
  semester: '',
  aidSource: '',
  remark: '',
  state: '',
})

const resetAidCardForm = (aid = {}) => {
  aidForm.id = aid.id || '';
  aidForm.studentId = aid.studentId || '';
  aidForm.aidType = aid.aidType || '';
  aidForm.aidAmount = aid.aidAmount || '';
  aidForm.semester = aid.semester || '';
  aidForm.aidSource = aid.aidSource || '';
  aidForm.remark = aid.remark || '';
  aidForm.state = aid.state || '待领取';
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.dialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetAidCardForm();
    } else {
      resetAidCardForm(props.pageParam.row);
    }
  }
});

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        aidForm.state = '待领取'
        const res = await addStudentFinancialAidApi(aidForm)
        if (res.code === 200) {
          ElMessage.success("添加资助记录成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        const res = await editStudentFinancialAidByIdApi(aidForm)
        if (res.code === 200) {
          ElMessage.success("更新资助记录成功！")
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