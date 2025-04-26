<template>
  <!-- 查询card -->
  <StayQuery :student-list="studentList" :dormitory-list="dormitoryList" :query-form="queryForm"
             :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <StayTable :stay-list="stayList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {
  getDormitoryListApi, getDormitoryStayRequestPageByConditionApi
} from "@/api/dormitory.js";
import {ElMessage} from "element-plus";
import {getUserListByRoleApi} from "@/api/user.js";
import StayQuery from "@/components/dormitory/stay/StayQuery.vue";
import StayTable from "@/components/dormitory/stay/StayTable.vue";
import Pagination from "@/components/Pagination.vue";

// 查询表单
const queryForm = reactive({
  studentId: '',
  dormitoryId: '',
  approvalStatus: '',
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
// 数据
const stayList = ref([])
// 获取用户数据
const getStayList = async () => {
  const res = await getDormitoryStayRequestPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    stayList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    stayList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getStayList();
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

const dormitoryList = ref([])
const getDormitoryList = async () => {
  const res = await getDormitoryListApi()
  if (res.code === 200) {
    dormitoryList.value = res.data
  } else {
    dormitoryList.value = []
  }
}

onMounted(() => {
  getStayList()
  getDormitoryList()
  getStudentList()
})
</script>

<style scoped>

</style>