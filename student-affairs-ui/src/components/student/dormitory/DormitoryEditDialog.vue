<template>
  <el-dialog v-model="pageParam.dialogVisible" title="办理入住" width="600">
    <el-form :model="dormitoryForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="dormitoryId" label="宿舍">
        <el-select v-model="dormitoryForm.dormitoryId" placeholder="请选择宿舍" style="width: 200px">
          <el-option v-for="item in dormitoryArray" :key="item.id" :label="item.buildingName + '-' + item.dormitoryName" :value="item.id"/>
        </el-select>
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
import {addStudentDormitoryApi} from "@/api/student.js";
import {getDormitoryListApi} from "@/api/dormitory.js";

const userStore = useUserStore()
const props = defineProps(['dormitoryList', 'pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  studentId: [requiredRule],
  dormitoryId: [requiredRule],
};

// 表单
const dormitoryForm = reactive({
  id: '',
  studentId: '',
  dormitoryId: '',
})

const resetRecordForm = (dormitory = {}) => {
  dormitoryForm.id = dormitory.id || '';
  dormitoryForm.studentId = userStore.user.id;
  dormitoryForm.dormitoryId = dormitory.dormitoryId || '';
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
      const res = await addStudentDormitoryApi(dormitoryForm)
      if (res.code === 200) {
        ElMessage.success("办理宿舍入住成功！")
        emit('queryAgain', 1, props.pageParam.pageSize)
      }
      props.pageParam.dialogVisible = false
    }
  })
}

const dormitoryArray = ref([])
const getDormitoryList = async () => {
  const res = await getDormitoryListApi()
  if (res.code === 200) {
    dormitoryArray.value = res.data
  } else {
    dormitoryArray.value = []
  }
}

onMounted(() => {
  getDormitoryList()
})
</script>

<style scoped>

</style>