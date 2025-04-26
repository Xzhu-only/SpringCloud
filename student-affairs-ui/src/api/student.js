import request from '../util/request.js';

// 分页条件查询校园卡信息
export function getCampusCardPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/campus_card/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加校园卡
export function addCampusCardApi(campusCardData) {
    return request({
        method: 'post',
        url: '/campus_card',
        data: campusCardData,  // 添加校园卡的数据
    });
}

// 删除校园卡
export function deleteCampusCardByIdApi(id) {
    return request({
        method: 'delete',
        url: `/campus_card/${id}`,
    });
}

// 更新校园卡
export function editCampusCardByIdApi(campusCardData) {
    return request({
        method: 'put',
        url: '/campus_card',
        data: campusCardData,  // 更新校园卡的数据
    });
}


// 分页条件查询奖惩记录信息
export function getStudentAwardPunishmentPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/student/award_punishment/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加奖惩记录
export function addStudentAwardPunishmentApi(studentAwardPunishmentData) {
    return request({
        method: 'post',
        url: '/student/award_punishment',
        data: studentAwardPunishmentData,  // 添加奖惩记录的数据
    });
}

// 删除奖惩记录
export function deleteStudentAwardPunishmentByIdApi(id) {
    return request({
        method: 'delete',
        url: `/student/award_punishment/${id}`,
    });
}

// 更新奖惩记录
export function editStudentAwardPunishmentByIdApi(studentAwardPunishmentData) {
    return request({
        method: 'put',
        url: '/student/award_punishment',
        data: studentAwardPunishmentData,  // 更新奖惩记录的数据
    });
}


// 分页条件查询学生证信息
export function getStudentCardPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/student/card/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加学生证
export function addStudentCardApi(studentCardData) {
    return request({
        method: 'post',
        url: '/student/card',
        data: studentCardData,  // 添加学生证的数据
    });
}

// 删除学生证
export function deleteStudentCardByIdApi(id) {
    return request({
        method: 'delete',
        url: `/student/card/${id}`,
    });
}

// 更新学生证
export function editStudentCardByIdApi(studentCardData) {
    return request({
        method: 'put',
        url: '/student/card',
        data: studentCardData,  // 更新学生证的数据
    });
}


// 分页条件查询学生宿舍记录信息
export function getStudentDormitoryPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/student/dormitory/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加学生宿舍记录
export function addStudentDormitoryApi(studentDormitoryData) {
    return request({
        method: 'post',
        url: '/student/dormitory',
        data: studentDormitoryData,  // 添加学生宿舍记录的数据
    });
}

// 删除学生宿舍记录
export function deleteStudentDormitoryByIdApi(id) {
    return request({
        method: 'delete',
        url: `/student/dormitory/${id}`,
    });
}

// 更新学生宿舍记录
export function editStudentDormitoryByIdApi(studentDormitoryData) {
    return request({
        method: 'put',
        url: '/student/dormitory',
        data: studentDormitoryData,  // 更新学生宿舍记录的数据
    });
}


// 分页条件查询资助信息记录
export function getStudentFinancialAidPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/student/financial_aid/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加资助信息记录
export function addStudentFinancialAidApi(studentFinancialAidData) {
    return request({
        method: 'post',
        url: '/student/financial_aid',
        data: studentFinancialAidData,  // 添加资助信息记录的数据
    });
}

// 删除资助信息记录
export function deleteStudentFinancialAidByIdApi(id) {
    return request({
        method: 'delete',
        url: `/student/financial_aid/${id}`,
    });
}

// 更新资助信息记录
export function editStudentFinancialAidByIdApi(studentFinancialAidData) {
    return request({
        method: 'put',
        url: '/student/financial_aid',
        data: studentFinancialAidData,  // 更新资助信息记录的数据
    });
}


// 分页条件查询健康记录
export function getStudentHealthRecordPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/student/health_record/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加健康记录
export function addStudentHealthRecordApi(studentHealthRecordData) {
    return request({
        method: 'post',
        url: '/student/health_record',
        data: studentHealthRecordData,  // 健康记录数据
    });
}

// 删除健康记录
export function deleteStudentHealthRecordByIdApi(id) {
    return request({
        method: 'delete',
        url: `/student/health_record/${id}`,
    });
}

// 更新健康记录
export function editStudentHealthRecordByIdApi(studentHealthRecordData) {
    return request({
        method: 'put',
        url: '/student/health_record',
        data: studentHealthRecordData,  // 更新后的健康记录数据
    });
}

// 分页条件查询登记记录
export function getStudentRegistrationPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/student/registration/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加新生登记记录
export function addStudentRegistrationApi(studentRegistrationData) {
    return request({
        method: 'post',
        url: '/student/registration',
        data: studentRegistrationData,  // 新增的新生登记数据
    });
}

// 删除新生登记记录
export function deleteStudentRegistrationByIdApi(id) {
    return request({
        method: 'delete',
        url: `/student/registration/${id}`,
    });
}

// 更新新生登记记录
export function editStudentRegistrationByIdApi(studentRegistrationData) {
    return request({
        method: 'put',
        url: '/student/registration',
        data: studentRegistrationData,  // 更新的新生登记数据
    });
}

// 添加学籍
export function addStudentStatusApi(studentStatusData) {
    return request({
        method: 'post',
        url: '/student/status',
        data: studentStatusData,  // 传递学籍数据
    });
}

// 删除学籍
export function deleteStudentStatusByIdApi(id) {
    return request({
        method: 'delete',
        url: `/student/status/${id}`,
    });
}

// 修改学籍
export function editStudentStatusByIdApi(studentStatusData) {
    return request({
        method: 'put',
        url: '/student/status',
        data: studentStatusData,  // 传递更新后的学籍数据
    });
}

// 查询学籍信息
export function getStudentStatusApi(userId) {
    return request({
        method: 'get',
        url: `/student/status/get/${userId}`,
    });
}