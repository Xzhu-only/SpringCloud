<template>
  <div>
    <el-container>
      <!-- 侧边栏菜单 -->
      <el-aside width="220px">
        <AsideMenu />
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 15px">
          <HeaderMenu/>
        </el-header>

        <el-main>
          <!-- 导航面包屑 -->
          <Breadcrumb v-if="route.path !== '/home'"/>
          <!-- 主要页面 -->
          <router-view ></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import HeaderMenu from "@/layout/components/HeaderMenu.vue";
import AsideMenu from "@/layout/components/AsideMenu.vue";
import Breadcrumb from "@/components/Breadcrumb.vue";
import {useRoute} from "vue-router";

const route = useRoute()

</script>

<style scoped>
.el-container {
  height: 100vh;
  .el-aside {
    background-color: rgb(50.8, 116.6, 184.5);
    .el-menu {
      border-right: none;
    }
  }
  .el-main, .el-footer {
    background-color: #fff;
    --el-main-padding: 0px;
  }
}
</style>