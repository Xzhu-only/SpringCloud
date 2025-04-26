<template>
  <el-dialog v-model="pageParam.dialogVisible" :title="pageParam.addOrEditFlag ? '添加检查记录' : '编辑检查记录'" width="600">
    <el-form :model="cleanForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="dormitoryId" label="宿舍">
        <el-select v-model="cleanForm.dormitoryId" placeholder="请选择宿舍" style="width: 200px">
          <el-option v-for="item in dormitoryList" :key="item.id" :label="item.buildingName + '-' + item.dormitoryName" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item prop="cleanlinessStatus" label="卫生状况">
        <el-select v-model="cleanForm.cleanlinessStatus" placeholder="请选择卫生状况" style="width: 200px">
          <el-option label="合格" value="合格"/>
          <el-option label="不合格" value="不合格"/>
        </el-select>
      </el-form-item>
      <el-form-item prop="checkResult" label="结果描述">
        <el-input v-model="cleanForm.checkResult" placeholder="请输入检查结果描述" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="remark" label="备注">
        <el-input v-model="cleanForm.remark" type="textarea" :rows="4" placeholder="备注（可选）" autocomplete="off" />
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
import {addDormitoryCleanlinessCheckApi, editDormitoryCleanlinessCheckByIdApi} from "@/api/dormitory.js";

const userStore = useUserStore()
const props = defineProps(['dormitoryList', 'pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  dormitoryId: [requiredRule],
  cleanlinessStatus: [requiredRule],
  checkResult: [requiredRule],
};

// 表单
const cleanForm = reactive({
  id: '',
  dormitoryId: '',
  cleanlinessStatus: '',
  checkResult: '',
  remark: '',
})

const resetCleanForm = (clean = {}) => {
  cleanForm.id = clean.id || '';
  cleanForm.dormitoryId = clean.dormitoryId || '';
  cleanForm.cleanlinessStatus = clean.cleanlinessStatus || '';
  cleanForm.checkResult = clean.checkResult || '';
  cleanForm.remark = clean.remark || '';
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.dialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetCleanForm();
    } else {
      resetCleanForm(props.pageParam.row);
    }
  }
});

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        const res = await addDormitoryCleanlinessCheckApi(cleanForm)
        if (res.code === 200) {
          ElMessage.success("添加检查记录成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        const res = await editDormitoryCleanlinessCheckByIdApi(cleanForm)
        if (res.code === 200) {
          ElMessage.success("更新检查记录成功！")
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