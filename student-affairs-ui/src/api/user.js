import request from '../util/request.js';

// 用户注册
export function userRegisterApi(registerData) {
    return request({
        method: 'post',
        url: '/user/register',
        data: registerData,  // 将用户注册数据传递到后端
    });
}

// 用户登录
export function userLoginApi(loginData) {
    return request({
        method: 'post',
        url: '/user/login',
        data: loginData,  // 将登录数据传递到后端
    });
}

// 查询登录信息
export function getLoginInfoApi() {
    return request({
        method: 'get',
        url: '/user/loginInfo',
    });
}

// 分页条件查询用户列表
export function getUserPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/user/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 根据角色查询用户列表
export function getUserListByRoleApi(role) {
    return request({
        method: 'get',
        url: `/user/list/${role}`,
    });
}

// 添加用户
export function addUserApi(userData) {
    return request({
        method: 'post',
        url: '/user',
        data: userData,  // 添加用户的数据
    });
}

// 删除用户
export function deleteUserByIdApi(id) {
    return request({
        method: 'delete',
        url: `/user/${id}`,
    });
}

// 更新用户信息
export function editUserByIdApi(userData) {
    return request({
        method: 'put',
        url: '/user',
        data: userData,  // 更新用户的数据
    });
}

// 查询毕业流程步骤
export function getGraduateActive() {
    return request({
        method: 'get',
        url: `/user/graduate/active`,
    });
}