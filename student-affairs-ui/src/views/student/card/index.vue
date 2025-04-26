<template>
  <!-- 查询card -->
  <CardQuery :query-form="queryForm" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <CardTable :card-list="cardList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
  <CardEditDialog :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {getStudentCardPageByConditionApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import Pagination from "@/components/Pagination.vue";
import CardQuery from "@/components/student/card/CardQuery.vue";
import CardTable from "@/components/student/card/CardTable.vue";
import CardEditDialog from "@/components/student/card/CardEditDialog.vue";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
// 查询表单
const queryForm = reactive({
  cardNumber: '',
  state: '',
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
const cardList = ref([])
// 获取用户数据
const getStudentCardList = async () => {
  const res = await getStudentCardPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    cardList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    cardList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getStudentCardList();
}

onMounted(() => {
  if (userStore.user.role === 1) {
    queryForm.studentId = userStore.user.id
  }
  getStudentCardList()
})
</script>

<style scoped>

</style>