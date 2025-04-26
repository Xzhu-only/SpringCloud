<template>
  <!-- 查询card -->
  <DisciplineQuery :dormitory-list="dormitoryList" :query-form="queryForm" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <DisciplineTable :record-list="recordList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
  <DisciplineEditDialog :dormitory-list="dormitoryList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {
  getDormitoryDisciplineRecordPageByConditionApi,
  getDormitoryListApi
} from "@/api/dormitory.js";
import DisciplineTable from "@/components/dormitory/discipline/DisciplineTable.vue";
import Pagination from "@/components/Pagination.vue";
import DisciplineQuery from "@/components/dormitory/discipline/DisciplineQuery.vue";
import DisciplineEditDialog from "@/components/dormitory/discipline/DisciplineEditDialog.vue";
import {ElMessage} from "element-plus";

// 查询表单
const queryForm = reactive({
  dormitoryId: '',
  incidentType: '',
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
const recordList = ref([])
// 获取用户数据
const getRecordList = async () => {
  const res = await getDormitoryDisciplineRecordPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    recordList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    recordList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getRecordList();
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
  getRecordList()
  getDormitoryList()
})
</script>

<style scoped>

</style>