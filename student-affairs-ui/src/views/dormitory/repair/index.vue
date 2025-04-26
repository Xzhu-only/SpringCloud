<template>
  <!-- 查询card -->
  <RepairQuery :dormitory-list="dormitoryList" :query-form="queryForm" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <RepairTable :repair-list="repairList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {
  getDormitoryListApi, getDormitoryRepairRequestPageByConditionApi
} from "@/api/dormitory.js";
import {ElMessage} from "element-plus";
import RepairQuery from "@/components/dormitory/repair/RepairQuery.vue";
import RepairTable from "@/components/dormitory/repair/RepairTable.vue";
import Pagination from "@/components/Pagination.vue";

// 查询表单
const queryForm = reactive({
  dormitoryId: '',
  issueType: '',
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
const repairList = ref([])
// 获取用户数据
const getRepairList = async () => {
  const res = await getDormitoryRepairRequestPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    repairList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    repairList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getRepairList();
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
  getRepairList()
  getDormitoryList()
})
</script>

<style scoped>

</style>