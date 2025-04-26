<template>
  <!-- 查询card -->
  <UserQuery :query-form="queryForm" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <UserTable :user-list="userList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
  <UserEditDialog :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 学籍调动弹窗 -->
  <UserStatusDialog :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {getUserPageByConditionApi} from "@/api/user.js";
import UserTable from "@/components/user/UserTable.vue";
import Pagination from "@/components/Pagination.vue";
import UserQuery from "@/components/user/UserQuery.vue";
import UserEditDialog from "@/components/user/UserEditDialog.vue";
import {ElMessage} from "element-plus";
import UserStatusDialog from "@/components/user/UserStatusDialog.vue";

// 查询表单
const queryForm = reactive({
  username: '',
  role: '',
  state: ''
})
// 分页参数
const pageParam = reactive({
  pageNum: 1,
  pageSize: 5,
  total: 0,
  dialogVisible: false,
  addOrEditFlag: false,
  row: {},
  statusDialogVisible: false,
  studentStatus: {}
})
// 用户数据
const userList = ref([])
// 获取用户数据
const getUserList = async () => {
  const res = await getUserPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    userList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    userList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getUserList();
}

onMounted(() => {
  getUserList()
})
</script>

<style scoped>

</style>