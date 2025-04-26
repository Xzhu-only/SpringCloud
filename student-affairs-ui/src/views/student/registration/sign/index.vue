<template>
  <!-- 展示列表 -->
  <SignTable :registration-list="registrationList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import Pagination from "@/components/Pagination.vue";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";
import {getStudentRegistrationPageByConditionApi} from "@/api/student.js";
import SignTable from "@/components/student/sign/SignTable.vue";

const userStore = useUserStore()
// 查询表单
const queryForm = reactive({
  userId: '',
})
// 分页参数
const pageParam = reactive({
  pageNum: 1,
  pageSize: 5,
  total: 0,
  dialogVisible: false,
  addOrEditFlag: false,
  row: {}
})
// 用户数据
const registrationList = ref([])
// 获取用户数据
const getRegistrationList = async () => {
  const res = await getStudentRegistrationPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    registrationList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    registrationList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getRegistrationList();
}

onMounted(() => {
  if (userStore.user.role === 1) {
    queryForm.userId = userStore.user.id
  }
  getRegistrationList()
})
</script>

<style scoped>

</style>