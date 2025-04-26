<template>
  <!-- 查询card -->
  <DormitoryQuery :teacher-list="teacherList" :query-form="queryForm" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <DormitoryTable :dormitory-list="dormitoryList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
  <DormitoryEditDialog :teacher-list="teacherList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {getDormitoryPageByConditionApi} from "@/api/dormitory.js";
import DormitoryTable from "@/components/dormitory/DormitoryTable.vue";
import Pagination from "@/components/Pagination.vue";
import DormitoryQuery from "@/components/dormitory/DormitoryQuery.vue";
import DormitoryEditDialog from "@/components/dormitory/DormitoryEditDialog.vue";
import {ElMessage} from "element-plus";
import {getUserListByRoleApi} from "@/api/user.js";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
// 查询表单
const queryForm = reactive({
  dormitoryName: '',
  teacherId: '',
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
const dormitoryList = ref([])
// 获取用户数据
const getDormitoryList = async () => {
  const res = await getDormitoryPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    dormitoryList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    dormitoryList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getDormitoryList();
}

const teacherList = ref([])
const getTeacherList = async () => {
  const res = await getUserListByRoleApi(2)
  if (res.code === 200) {
    teacherList.value = res.data
  } else {
    teacherList.value = []
  }
}

onMounted(() => {
  if (userStore.user.role === 2) {
    queryForm.teacherId = userStore.user.id
  }
  getDormitoryList()
  getTeacherList()
})
</script>

<style scoped>

</style>