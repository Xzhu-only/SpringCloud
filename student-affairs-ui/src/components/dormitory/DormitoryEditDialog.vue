<template>
  <el-dialog v-model="pageParam.dialogVisible" :title="pageParam.addOrEditFlag ? '添加宿舍' : '编辑宿舍'" width="600">
    <el-form :model="dormitoryForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="dormitoryName" label="宿舍名称">
        <el-input v-model="dormitoryForm.dormitoryName" placeholder="请输入宿舍名" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="buildingName" label="宿舍楼">
        <el-input v-model="dormitoryForm.buildingName" placeholder="请输入宿舍楼" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="capacity" label="最大容纳人数">
        <el-input-number v-model="dormitoryForm.capacity" :min="dormitoryForm.currentOccupancy" size="small"/>
      </el-form-item>
      <el-form-item v-if="userStore.user.role === 0" prop="teacherId" label="负责老师">
        <el-select v-model="dormitoryForm.teacherId" placeholder="请选择负责老师" style="width: 240px">
          <el-option v-for="item in teacherList" :key="item.id" :label="item.username" :value="item.id"/>
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
import {reactive, ref, watch} from "vue";
import {addDormitoryApi, editDormitoryByIdApi} from "@/api/dormitory.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const props = defineProps(['teacherList', 'pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  dormitoryName: [requiredRule],
  buildingName: [requiredRule],
  capacity: [requiredRule],
};

// 表单
const dormitoryForm = reactive({
  id: '',
  dormitoryName: '',
  buildingName: '',
  capacity: '',
  currentOccupancy: '',
  teacherId: ''
})

const resetDormitoryForm = (dormitory = {}) => {
  dormitoryForm.id = dormitory.id || '';
  dormitoryForm.dormitoryName = dormitory.dormitoryName || '';
  dormitoryForm.buildingName = dormitory.buildingName || '';
  dormitoryForm.capacity = dormitory.capacity || '';
  dormitoryForm.currentOccupancy = dormitory.currentOccupancy;
  dormitoryForm.teacherId = dormitory.teacherId;
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.dialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetDormitoryForm();
    } else {
      resetDormitoryForm(props.pageParam.row);
    }
  }
});

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        //如果是老师，则复制自己的ID
        if (userStore.user.role === 2) {
          dormitoryForm.teacherId = userStore.user.id
        }
        const res = await addDormitoryApi(dormitoryForm)
        if (res.code === 200) {
          ElMessage.success("添加宿舍成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        const res = await editDormitoryByIdApi(dormitoryForm)
        if (res.code === 200) {
          ElMessage.success("更新宿舍成功！")
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