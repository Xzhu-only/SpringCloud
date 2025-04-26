<template>
  <!-- 查询card -->
  <CourseQuery :query-form="queryForm" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <CourseTable :course-list="courseList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
  <CourseEditDialog :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {getCoursePageByConditionApi} from "@/api/course.js";
import CourseTable from "@/components/course/CourseTable.vue";
import Pagination from "@/components/Pagination.vue";
import CourseQuery from "@/components/course/CourseQuery.vue";
import CourseEditDialog from "@/components/course/CourseEditDialog.vue";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
// 查询表单
const queryForm = reactive({
  courseName: '',
  courseCode: '',
  teacherId: ''
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
const courseList = ref([])
// 获取用户数据
const getCourseList = async () => {
  const res = await getCoursePageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    courseList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    courseList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getCourseList();
}

onMounted(() => {
  if (userStore.user.role === 2) {
    queryForm.teacherId = userStore.user.id
  }
  getCourseList()
})
</script>

<style scoped>

</style>