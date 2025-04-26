<template>
  <el-affix position="bottom" v-if="pageParam.total > pageParam.pageSize">
    <el-card class="pagination">
      <el-pagination v-model:current-page="pageParam.pageNum" v-model:page-size="pageParam.pageSize" :page-sizes="[5, 10, 15, 20]"
                     layout="total, sizes, prev, pager, next, jumper" :total="pageParam.total" background
                     @size-change="handleSizeChange" @current-change="handlePageNumChange" pager-count="10"/>
    </el-card>
  </el-affix>
</template>

<script setup>
const props = defineProps(['pageParam'])
const emit = defineEmits()

const handlePageNumChange = (newPageNum) => {
  emit('queryAgain', newPageNum, props.pageParam.pageSize)
}
const handleSizeChange = (newPageSize) => {
  emit('queryAgain', props.pageParam.pageNum, newPageSize)
}
</script>

<style scoped>
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 70px;
}
</style>