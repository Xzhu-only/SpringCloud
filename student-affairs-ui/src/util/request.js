import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

// 创建axios实例，定义相关配置
const axiosInstance = axios.create({
    baseURL: '/api',
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    },
});

// 添加请求拦截器（可以在此做统一的处理，如加上 token 等）
axiosInstance.interceptors.request.use(config => {
    // 添加认证信息或其他自定义请求头
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = token;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// 添加响应拦截器（统一处理错误信息）
axiosInstance.interceptors.response.use(response => {
    if (response.data.code === 200) {
        return response.data;
    }
    ElMessage.error(response.data.msg || '系统服务异常');
    if (response.data.msg === '用户未登录！') {
        router.push('/login');
    }
    return Promise.reject(response.data);
}, error => {
    if (error.response.status === 401) {
        ElMessage.error('您尚未登录');
        router.push('/login');
    } else {
        ElMessage.error('系统服务异常');
    }
    return Promise.reject(error);
});

export default axiosInstance;
