<template>
  <!-- 查询aid -->
  <FinancialQuery :student-list="studentList" :query-form="queryForm" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <FinancialTable :aid-list="aidList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
  <FinancialEditDialog :student-list="studentList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {getStudentFinancialAidPageByConditionApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import Pagination from "@/components/Pagination.vue";
import {getUserListByRoleApi} from "@/api/user.js";
import FinancialQuery from "@/components/student/financial/FinancialQuery.vue";
import FinancialTable from "@/components/student/financial/FinancialTable.vue";
import FinancialEditDialog from "@/components/student/financial/FinancialEditDialog.vue";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
// 查询表单
const queryForm = reactive({
  studentId: '',
  aidType: '',
  state: '',
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
const aidList = ref([])
// 获取用户数据
const getAidList = async () => {
  const res = await getStudentFinancialAidPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    aidList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    aidList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getAidList();
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
  if (userStore.user.role === 1) {
    queryForm.studentId = userStore.user.id
  }
  getAidList()
  getStudentList()
})
</script>

<style scoped>

</style>