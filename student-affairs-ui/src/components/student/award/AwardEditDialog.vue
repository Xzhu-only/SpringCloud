<template>
  <el-dialog v-model="pageParam.dialogVisible" :title="pageParam.addOrEditFlag ? '添加奖惩记录' : '编辑奖惩记录'" width="600">
    <el-form :model="awardForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="type" label="奖惩类型">
        <el-radio-group v-model="awardForm.type">
          <el-radio value="奖励">奖励</el-radio>
          <el-radio value="惩罚">惩罚</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="studentId" label="奖惩学生">
        <el-select v-model="awardForm.studentId" placeholder="请选择奖惩学生" style="width: 240px">
          <el-option v-for="item in studentList" :key="item.id" :label="item.username" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item prop="reason" label="奖惩原因">
        <el-input v-model="awardForm.reason" placeholder="请输入奖惩原因" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="details" label="详情">
        <el-input v-model="awardForm.details" type="textarea" :rows="4" placeholder="请输入详情" autocomplete="off" />
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
  addStudentAwardPunishmentApi,
  editStudentAwardPunishmentByIdApi
} from "@/api/student.js";

const userStore = useUserStore()
const props = defineProps(['studentList', 'pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  studentId: [requiredRule],
  type: [requiredRule],
  reason: [requiredRule],
  details: [requiredRule],
};

// 表单
const awardForm = reactive({
  id: '',
  studentId: '',
  type: '',
  reason: '',
  details: '',
})

const resetAwardCardForm = (award = {}) => {
  awardForm.id = award.id || '';
  awardForm.studentId = award.studentId || '';
  awardForm.type = award.type || '';
  awardForm.reason = award.reason || '';
  awardForm.details = award.details || '';
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.dialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetAwardCardForm();
    } else {
      resetAwardCardForm(props.pageParam.row);
    }
  }
});

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        const res = await addStudentAwardPunishmentApi(awardForm)
        if (res.code === 200) {
          ElMessage.success("添加奖惩记录成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        const res = await editStudentAwardPunishmentByIdApi(awardForm)
        if (res.code === 200) {
          ElMessage.success("更新奖惩记录成功！")
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