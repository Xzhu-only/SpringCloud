<template>
  <el-card class="list-table">
    <h3 style="margin-top: -6px">我的校园卡：</h3>
    <el-table :data="cardList" max-height="550" style="width: 100%">
      <el-table-column label="校园卡编号" prop="cardNumber"/>
      <el-table-column label="所属学生" prop="studentName"/>
      <el-table-column label="余额" prop="balance">
        <template #default="scope">
          {{scope.row.balance}}元
        </template>
      </el-table-column>
      <el-table-column label="校园卡状态" prop="state">
        <template #default="scope">
          <el-tag v-if="scope.row.state === '使用中'" type="success">使用中</el-tag>
          <el-tag v-if="scope.row.state === '已失效'" type="danger">已失效</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="发放时间" prop="issuedDate"/>
      <el-table-column label="最后充值时间" prop="lastRechargeDate"/>

      <el-table-column  label="操作" align="left" fixed="right" width="200">
        <template #default="scope">
          <el-button v-if="userStore.user.role !== 1" type="warning" icon="Edit" size="small"
                     @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm v-if="userStore.user.role !== 1" title="你确定要删除该校园卡吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" icon="Delete" size="small" >删除</el-button>
            </template>
          </el-popconfirm>

          <el-button v-if="userStore.user.role === 1" type="warning" icon="Edit" size="small"
                     @click="handleUp(scope.row)">充值</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

  <el-dialog v-model="upDialogVisible" title="校园卡充值" width="600">
    <el-form :model="campus" label-width="auto">
      <el-form-item prop="balance" label="充值金额">
        <el-input-number v-model="amount" :precision="2" :step="0.5" :min="0" />
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="upDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {deleteCampusCardByIdApi, editCampusCardByIdApi} from "@/api/student.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";
import {ref} from "vue";

const userStore = useUserStore()
const props = defineProps(['cardList', 'pageParam'])
const emit = defineEmits()

//处理编辑按钮响应
const handleEdit = (row) => {
  props.pageParam.dialogVisible = true
  props.pageParam.addOrEditFlag = false
  props.pageParam.row = row
}
//处理删除按钮响应
const handleDelete = async (cardId) => {
  const res = await deleteCampusCardByIdApi(cardId)
  if (res.code === 200) {
    ElMessage.success('删除成功！')
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}

//控制充值框弹出
const upDialogVisible = ref(false)
//充值金额
const amount = ref(0)
const campus = ref({})
//处理充值按钮响应
const handleUp = (row) => {
  campus.value = row
  upDialogVisible.value = true
  amount.value = 0
}
const onSubmit = async () => {
  if (amount.value === 0) {
    ElMessage.warning("请输入充值金额！")
    return;
  }
  campus.value.balance = campus.value.balance + amount.value
  const res = await editCampusCardByIdApi(campus.value)
  if (res.code === 200) {
    upDialogVisible.value = false
    ElMessage.success("充值成功！")
    emit('queryAgain', props.pageParam.pageNum, props.pageParam.pageSize)
  }
}
</script>

<style scoped>
.list-table {
  height: 100%;
}
</style>