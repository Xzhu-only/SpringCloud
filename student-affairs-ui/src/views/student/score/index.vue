<template>
  <!-- 查询card -->
  <ScoreQuery :student-list="studentList" :course-list="courseList" :query-form="queryForm"
              :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 展示列表 -->
  <ScoreTable :grade-list="gradeList" :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 列表分页 -->
  <Pagination :page-param="pageParam" @queryAgain="handleQueryAgain"/>
  <!-- 添加/编辑弹窗 -->
  <ScoreEditDialog :page-param="pageParam" @queryAgain="handleQueryAgain"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";
import {getCourseListApi, getCourseSelectionPageByConditionApi} from "@/api/course.js";
import ScoreQuery from "@/components/student/score/ScoreQuery.vue";
import ScoreTable from "@/components/student/score/ScoreTable.vue";
import Pagination from "@/components/Pagination.vue";
import ScoreEditDialog from "@/components/student/score/ScoreEditDialog.vue";
import {getUserListByRoleApi} from "@/api/user.js";

const userStore = useUserStore()
// 查询表单
const queryForm = reactive({
  studentId: '',
  courseId: '',
  semester: ''
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
const gradeList = ref([])
// 获取用户数据
const getGradeList = async () => {
  const res = await getCourseSelectionPageByConditionApi(pageParam.pageNum, pageParam.pageSize, queryForm);
  if (res.code === 200) {
    gradeList.value = res.data.records;
    pageParam.total = res.data.total;
  } else {
    gradeList.value = [];
    ElMessage.error('加载数据失败，请稍后再试');
  }
};
// 接受子组件发来的查询事件
const handleQueryAgain = (newPageNum, newPageSize) => {
  if (newPageNum !== undefined) pageParam.pageNum = newPageNum;
  if (newPageSize !== undefined) pageParam.pageSize = newPageSize;
  getGradeList();
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

const courseList = ref([])
const getCourseList = async () => {
  const res = await getCourseListApi()
  if (res.code === 200) {
    courseList.value = res.data
  } else {
    courseList.value = []
  }
}

onMounted(() => {
  if (userStore.user.role === 1) {
    queryForm.studentId = userStore.user.id
  }
  getGradeList()
  getCourseList()
  getStudentList()
})
</script>

<style scoped>

</style>