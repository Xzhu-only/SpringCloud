import { createRouter, createWebHistory } from 'vue-router'
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";
import {getLoginInfoApi} from "@/api/user.js";

// 基础固定路由
const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/register/index.vue')
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('@/layout/index.vue'),
    children: [
      {
        path: "",
        component: () => import('@/views/home/index.vue')
      },
      {
        path: '/user',
        name: 'User',
        component: () => import('@/views/user/index.vue')
      },
      {
        path: '/course',
        name: 'Course',
        component: () => import('@/views/course/index.vue')
      },
      {
        path: '/student/card',
        name: 'StudentCard',
        component: () => import('@/views/student/card/index.vue')
      },
      {
        path: '/student/campus',
        name: 'StudentCampus',
        component: () => import('@/views/student/campus/index.vue')
      },
      {
        path: '/student/score',
        name: 'StudentScore',
        component: () => import('@/views/student/score/index.vue')
      },
      {
        path: '/student/award',
        name: 'StudentAward',
        component: () => import('@/views/student/award/index.vue')
      },
      {
        path: '/student/financial',
        name: 'StudentFinancial',
        component: () => import('@/views/student/financial/index.vue')
      },
      {
        path: '/student/health',
        name: 'StudentHealth',
        component: () => import('@/views/student/health/index.vue')
      },
      {
        path: '/student/dormitory',
        name: 'StudentDormitory',
        component: () => import('@/views/student/dormitory/index.vue')
      },
      {
        path: '/student/registration',
        name: 'StudentRegistration',
        component: () => import('@/views/student/registration/index.vue')
      },
      {
        path: '/student/sign',
        name: 'StudentSign',
        component: () => import('@/views/student/registration/sign/index.vue')
      },
      {
        path: '/student/process',
        name: 'StudentProcess',
        component: () => import('@/views/student/process/index.vue')
      },
      {
        path: '/dormitory',
        name: 'Dormitory',
        component: () => import('@/views/dormitory/index.vue')
      },
      {
        path: '/dormitory/clean',
        name: 'DormitoryClean',
        component: () => import('@/views/dormitory/clean/index.vue')
      },
      {
        path: '/dormitory/discipline',
        name: 'DormitoryDiscipline',
        component: () => import('@/views/dormitory/discipline/index.vue')
      },
      {
        path: '/dormitory/repair',
        name: 'DormitoryRepair',
        component: () => import('@/views/dormitory/repair/index.vue')
      },
      {
        path: '/dormitory/stay',
        name: 'DormitoryStay',
        component: () => import('@/views/dormitory/stay/index.vue')
      },
      {
        path: '/dormitory/repair/apply',
        name: 'DormitoryRepairApply',
        component: () => import('@/views/dormitory/repair/apply/index.vue')
      },
      {
        path: '/dormitory/stay/apply',
        name: 'DormitoryStayApply',
        component: () => import('@/views/dormitory/stay/apply/index.vue')
      },
      {
        path: '/graduate/process',
        name: 'GraduateProcess',
        component: () => import('@/views/graduate/process/index.vue')
      },
      {
        path: '/statistic',
        name: 'Statistic',
        component: () => import('@/views/statistic/index.vue')
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: routes
})

// 前置路由守卫，装载动态路由信息
router.beforeEach(async (to, from, next) => {
  const userStore = useUserStore()
  // 非登录或注册页面都需要判断登录信息
  if (!(to.path === '/login' || to.path === '/register')) {
    //发送请求查询登录信息
    const res = await getLoginInfoApi()
    if (res.code === 200 && res.data) {
      userStore.setUser(res.data)
      next();
    } else {
      ElMessage.warning("需要登录才能访问平台！");
      next({ name: 'Login' });
    }
  } else {
    next();
  }
});

export default router
