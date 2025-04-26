<template>
  <el-menu :default-active="activeIndex" class="aside-menu" style="margin-left: -10px"
           active-text-color="#E6A23C" background-color="rgb(50.8, 116.6, 184.5)" text-color="#fff" router>
    <el-menu-item>
      <h2 style="color: white;margin-left: 20px">学工服务平台</h2>
    </el-menu-item>
    <!-- 动态菜单项 -->
    <template v-for="(menu, index) in dynamicMenus" :key="index">
      <el-sub-menu v-if="menu.children" :index="'submenu-' + index">
        <template #title>
          <el-icon><component :is="getIconComponent(menu.icon)"/></el-icon>
          <span>{{ menu.label }}</span>
        </template>
        <el-menu-item v-for="(child, subIndex) in menu.children" :key="subIndex" :index="child.path">
          <el-icon><component :is="getIconComponent(child.icon)"/></el-icon>
          <span>{{ child.label }}</span>
        </el-menu-item>
      </el-sub-menu>
      <el-menu-item v-else :index="menu.path">
        <el-icon><component :is="getIconComponent(menu.icon)"/></el-icon>
        <span>{{ menu.label }}</span>
      </el-menu-item>
    </template>
  </el-menu>
</template>

<script setup>
import {computed, onMounted, ref} from "vue";
import {useUserStore} from "@/stores/user.js";
import { menuData } from '../menu.js'  // 引入菜单数据
import * as icons from '@element-plus/icons-vue'
import {useRoute} from "vue-router";

const route = useRoute()
const userStore = useUserStore()
const activeIndex = ref('')

// 根据用户角色计算动态菜单
const dynamicMenus = computed(() => {
  switch (userStore.user.role) {
    case 0:  // 管理员
      return menuData.admin
    case 1:  // 学生
      return menuData.student
    case 2:  // 教师
      return menuData.teacher
    default:
      return []
  }
})

// 动态匹配图标组件
const getIconComponent = (iconName) => {
  return icons[iconName] || null  // 如果图标存在，返回对应的组件，否则返回 null
}
onMounted(() => {
  activeIndex.value = route.path
})
</script>

<style scoped>

</style>