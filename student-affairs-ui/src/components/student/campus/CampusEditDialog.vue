<template>
  <el-dialog v-model="pageParam.dialogVisible" :title="pageParam.addOrEditFlag ? '添加校园卡' : '编辑校园卡'" width="600">
    <el-form :model="cardForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="balance" label="充值金额">
        <el-input-number v-model="cardForm.balance" :precision="2" :step="0.5" :min="0" />
      </el-form-item>
      <el-form-item v-if="pageParam.addOrEditFlag && userStore.user.role !== 1" prop="studentId" label="办理学生">
        <el-select v-model="cardForm.studentId" placeholder="请选择办理学生" style="width: 240px">
          <el-option v-for="item in studentList" :key="item.id" :label="item.username" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item v-if="!pageParam.addOrEditFlag" prop="state" label="状态">
        <el-radio-group v-model="cardForm.state">
          <el-radio value="使用中">使用中</el-radio>
          <el-radio value="已失效">已失效</el-radio>
        </el-radio-group>
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
import {onMounted, reactive, ref, watch} from "vue";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";
import {getUserListByRoleApi} from "@/api/user.js";
import {addCampusCardApi, editCampusCardByIdApi} from "@/api/student.js";

const userStore = useUserStore()
const props = defineProps(['pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  balance: [requiredRule],
  state: [requiredRule],
  studentId: [requiredRule],
};

// 表单
const cardForm = reactive({
  id: '',
  balance: '',
  state: '',
  studentId: '',
})

const resetCampusCardForm = (card = {}) => {
  cardForm.id = card.id || '';
  cardForm.balance = card.balance;
  cardForm.state = card.state;
  cardForm.studentId = card.studentId || '';
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.dialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetCampusCardForm();
    } else {
      resetCampusCardForm(props.pageParam.row);
    }
  }
});

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        cardForm.state = "使用中"
        if (userStore.user.role === 1) {
          cardForm.studentId = userStore.user.id
        }
        const res = await addCampusCardApi(cardForm)
        if (res.code === 200) {
          ElMessage.success("添加校园卡成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        const res = await editCampusCardByIdApi(cardForm)
        if (res.code === 200) {
          ElMessage.success("更新校园卡成功！")
          emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
        }
      }
      props.pageParam.dialogVisible = false
    }
  })
}

const studentList = ref([])
const getStudentList = async () => {
  const res = await getUserListByRoleApi(1)
  if (res.code === 200) {
    studentList.value = res.data
  } else {
    studentList.value = []
  }
}

onMounted(() => {
  getStudentList()
})
</script>

<style scoped>

</style>