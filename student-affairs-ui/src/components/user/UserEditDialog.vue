<template>
  <el-dialog v-model="pageParam.dialogVisible" :title="pageParam.addOrEditFlag ? '添加用户' : '编辑用户'" width="600">
    <el-form :model="userForm" ref="formRef" :rules="rules" label-width="auto">
      <el-form-item prop="username" label="用户名">
        <el-input v-model="userForm.username" placeholder="请输入用户名" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="password" label="密码" v-if="pageParam.addOrEditFlag">
        <el-input v-model="userForm.password" placeholder="请输入密码" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="email" label="邮箱">
        <el-input v-model="userForm.email" placeholder="请输入邮箱" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="role" label="角色">
        <el-radio-group v-model="userForm.role">
          <el-radio :value="1">学生</el-radio>
          <el-radio :value="2">教师</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="role" label="状态" v-if="!pageParam.addOrEditFlag">
        <el-radio-group v-model="userForm.state">
          <el-radio :value="0">正常</el-radio>
          <el-radio :value="1">封禁</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="avatar" label="头像">
        <el-upload
            action="/api/file/upload/image"
            list-type="picture-card"
            :on-success="handleUploadSuccess"
            :on-error="handleUploadError"
            :file-list="fileList"
            :auto-upload="true"
            :limit="1">
          <el-icon class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
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
import {addUserApi, editUserByIdApi} from "@/api/user.js";
import {ElMessage} from "element-plus";
import {Plus} from "@element-plus/icons-vue";

const props = defineProps(['pageParam'])
const emit = defineEmits()

// 验证规则
const formRef = ref()
const requiredRule = { required: true, message: '该字段为必填项', trigger: 'blur' };
const rules = {
  username: [requiredRule, { min: 2, max: 15, message: '用户名长度必须介于2到15个字符之间', trigger: 'blur' }],
  password: [requiredRule, { min: 6, max: 15, message: '密码长度必须在6到15个字符之间', trigger: 'blur' }],
  email: [requiredRule, { pattern: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/, message: '请输入有效的电子邮件地址', trigger: 'blur' }],
  avatar: [requiredRule],
  state: [requiredRule],
  role: [requiredRule]
};

// 表单
const userForm = reactive({
  id: '',
  username: '',
  password: '',
  email: '',
  avatar: '',
  state: '',
  role: '',
})

const resetUserForm = (user = {}) => {
  userForm.id = user.id || '';
  userForm.username = user.username || '';
  userForm.password = user.password;
  userForm.email = user.email || '';
  userForm.avatar = user.avatar || '';
  userForm.state = user.state;
  userForm.role = user.role;
  fileList.value = user.avatar ? [{ url: user.avatar }] : [];
};
// 监听 addOrEditFlag 变化，并根据它的值动态赋值
watch(() => props.pageParam.dialogVisible, (newVal) => {
  if (newVal) {
    if (props.pageParam.addOrEditFlag) {
      resetUserForm();
    } else {
      resetUserForm(props.pageParam.row);
    }
  }
});

// 图片上传
const fileList = ref([]);
// 上传成功响应
const handleUploadSuccess = (response, file, fileList) => {
  userForm.avatar = response.data
  ElMessage.success('图片上传成功');
};
// 上传失败响应
const handleUploadError = (err) => {
  ElMessage.error('图像上传失败');
  fileList.value = [];
};

// 提交按钮响应
const onSubmit = async (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      if (props.pageParam.addOrEditFlag) {
        const res = await addUserApi(userForm)
        if (res.code === 200) {
          ElMessage.success("添加用户成功！")
          emit('queryAgain', 1, props.pageParam.pageSize)
        }
      } else {
        const res = await editUserByIdApi(userForm)
        if (res.code === 200) {
          ElMessage.success("更新用户成功！")
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