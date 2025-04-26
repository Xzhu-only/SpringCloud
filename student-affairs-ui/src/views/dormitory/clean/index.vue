<template>
  <!-- 查询card -->
  <CleanQuery :dormitory-list="dormitoryList" :query-form="queryForm" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <CleanTable :clean-list="cleanList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
  <CleanEditDialog :dormitory-list="dormitoryList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {getDormitoryCleanlinessCheckPageByConditionApi, getDormitoryListApi} from "@/api/dormitory.js";
import CleanTable from "@/components/dormitory/clean/CleanTable.vue";
import Pagination from "@/components/Pagination.vue";
import CleanQuery from "@/components/dormitory/clean/CleanQuery.vue";
import CleanEditDialog from "@/components/dormitory/clean/CleanEditDialog.vue";
import {ElMessage} from "element-plus";

// 查询表单
const queryForm = reactive({
  dormitoryId: '',
  cleanlinessStatus: '',
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
const cleanList = ref([])
// 获取用户数据
const getCleanList = async () => {
  const res = await getDormitoryCleanlinessCheckPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    cleanList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    cleanList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getCleanList();
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
  getCleanList()
  getDormitoryList()
})
</script>

<style scoped>

</style>