<template>
  <!-- 查询health -->
  <HealthQuery :student-list="studentList" :query-form="queryForm" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <HealthTable :health-list="healthList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
  <HealthEditDialog :student-list="studentList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {getStudentHealthRecordPageByConditionApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import Pagination from "@/components/Pagination.vue";
import {getUserListByRoleApi} from "@/api/user.js";
import HealthQuery from "@/components/student/health/HealthQuery.vue";
import HealthTable from "@/components/student/health/HealthTable.vue";
import HealthEditDialog from "@/components/student/health/HealthEditDialog.vue";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
// 查询表单
const queryForm = reactive({
  studentId: '',
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
const healthList = ref([])
// 获取用户数据
const getHealthList = async () => {
  const res = await getStudentHealthRecordPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    healthList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    healthList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getHealthList();
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
  getHealthList()
  getStudentList()
})
</script>

<style scoped>

</style>