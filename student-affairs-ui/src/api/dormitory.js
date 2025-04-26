import request from '../util/request.js';

// 分页条件查询宿舍信息
export function getDormitoryPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/dormitory/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 查询宿舍列表
export function getDormitoryListApi() {
    return request({
        method: 'get',
        url: '/dormitory/list',
    });
}

// 添加宿舍
export function addDormitoryApi(dormitoryData) {
    return request({
        method: 'post',
        url: '/dormitory',
        data: dormitoryData,  // 添加宿舍的数据
    });
}

// 删除宿舍
export function deleteDormitoryByIdApi(id) {
    return request({
        method: 'delete',
        url: `/dormitory/${id}`,
    });
}

// 更新宿舍信息
export function editDormitoryByIdApi(dormitoryData) {
    return request({
        method: 'put',
        url: '/dormitory',
        data: dormitoryData,  // 更新宿舍的数据
    });
}

// 查询单个宿舍信息
export function getDormitoryByIdApi(id) {
    return request({
        method: 'get',
        url: `/dormitory/${id}`,
    });
}


// 分页条件查询宿舍检查记录信息
export function getDormitoryCleanlinessCheckPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/dormitory/cleanliness_check/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加宿舍检查记录
export function addDormitoryCleanlinessCheckApi(dormitoryCleanlinessData) {
    return request({
        method: 'post',
        url: '/dormitory/cleanliness_check',
        data: dormitoryCleanlinessData,  // 添加宿舍检查记录的数据
    });
}

// 删除宿舍检查记录
export function deleteDormitoryCleanlinessCheckByIdApi(id) {
    return request({
        method: 'delete',
        url: `/dormitory/cleanliness_check/${id}`,
    });
}

// 更新宿舍检查记录
export function editDormitoryCleanlinessCheckByIdApi(dormitoryCleanlinessData) {
    return request({
        method: 'put',
        url: '/dormitory/cleanliness_check',
        data: dormitoryCleanlinessData,  // 更新宿舍检查记录的数据
    });
}


// 分页条件查询宿舍违纪记录信息
export function getDormitoryDisciplineRecordPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/dormitory/discipline_record/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加宿舍违纪记录
export function addDormitoryDisciplineRecordApi(dormitoryDisciplineData) {
    return request({
        method: 'post',
        url: '/dormitory/discipline_record',
        data: dormitoryDisciplineData,  // 添加宿舍违纪记录的数据
    });
}

// 删除宿舍违纪记录
export function deleteDormitoryDisciplineRecordByIdApi(id) {
    return request({
        method: 'delete',
        url: `/dormitory/discipline_record/${id}`,
    });
}

// 更新宿舍违纪记录
export function editDormitoryDisciplineRecordByIdApi(dormitoryDisciplineData) {
    return request({
        method: 'put',
        url: '/dormitory/discipline_record',
        data: dormitoryDisciplineData,  // 更新宿舍违纪记录的数据
    });
}


// 分页条件查询宿舍报修申请信息
export function getDormitoryRepairRequestPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/dormitory/repair/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加宿舍报修申请
export function addDormitoryRepairRequestApi(dormitoryRepairData) {
    return request({
        method: 'post',
        url: '/dormitory/repair',
        data: dormitoryRepairData,  // 添加宿舍报修申请的数据
    });
}

// 删除宿舍报修申请
export function deleteDormitoryRepairRequestByIdApi(id) {
    return request({
        method: 'delete',
        url: `/dormitory/repair/${id}`,
    });
}

// 更新宿舍报修申请
export function editDormitoryRepairRequestByIdApi(dormitoryRepairData) {
    return request({
        method: 'put',
        url: '/dormitory/repair',
        data: dormitoryRepairData,  // 更新宿舍报修申请的数据
    });
}


// 分页条件查询留校申请信息
export function getDormitoryStayRequestPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/dormitory/stay/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加留校申请
export function addDormitoryStayRequestApi(dormitoryStayData) {
    return request({
        method: 'post',
        url: '/dormitory/stay',
        data: dormitoryStayData,  // 添加留校申请的数据
    });
}

// 删除留校申请
export function deleteDormitoryStayRequestByIdApi(id) {
    return request({
        method: 'delete',
        url: `/dormitory/stay/${id}`,
    });
}

// 更新留校申请
export function editDormitoryStayRequestByIdApi(dormitoryStayData) {
    return request({
        method: 'put',
        url: '/dormitory/stay',
        data: dormitoryStayData,  // 更新留校申请的数据
    });
}