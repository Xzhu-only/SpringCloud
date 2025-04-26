<template>
  <el-card class="query-card">
    <el-form :inline="true" :model="queryForm" style="margin-bottom: -15px">
      <el-form-item label="学生">
        <el-select v-model="queryForm.studentId" placeholder="请输入查询的学生" style="width: 200px">
          <el-option v-for="item in studentList" :key="item.id" :label="item.username" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="宿舍">
        <el-select v-model="queryForm.dormitoryId" placeholder="请输入查询的宿舍" style="width: 200px">
          <el-option v-for="item in dormitoryList" :key="item.id" :label="item.buildingName + '-' + item.dormitoryName" :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="审批状态">
        <el-select v-model="queryForm.incidentType" placeholder="请选择审批状态" style="width: 200px">
          <el-option label="待审批" value="待审批"/>
          <el-option label="批准" value="批准"/>
          <el-option label="拒绝" value="拒绝"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-form-item>
          <el-button circle type="info" icon="Refresh" @click="resetQuery"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">查询</el-button>
        </el-form-item>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
const props = defineProps(['studentList', 'dormitoryList', 'queryForm', 'pageParam'])
const emit = defineEmits()

//处理查询按钮响应
const handleQuery = () => {
  emit('queryAgain', 1, props.pageParam.pageSize)
}
//处理重置按钮响应
const resetQuery = () => {
  props.queryForm.studentId = ''
  props.queryForm.dormitoryId = ''
  props.queryForm.approvalStatus = ''
  emit('queryAgain', 1, props.pageParam.pageSize)
}
</script>

<style scoped>
.query-card{
  margin-top: -20px;
  margin-bottom: 5px;
}
</style>