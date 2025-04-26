import request from '../util/request.js';

// 分页条件查询课程列表
export function getCoursePageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/course/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 查询课程列表
export function getCourseListApi() {
    return request({
        method: 'get',
        url: '/course/list',
    });
}

// 添加课程
export function addCourseApi(courseData) {
    return request({
        method: 'post',
        url: '/course',
        data: courseData,  // 添加课程的数据
    });
}

// 删除课程
export function deleteCourseByIdApi(id) {
    return request({
        method: 'delete',
        url: `/course/${id}`,
    });
}

// 更新课程信息
export function editCourseByIdApi(courseData) {
    return request({
        method: 'put',
        url: '/course',
        data: courseData,  // 更新课程的数据
    });
}


// 添加成绩
export function addCourseGradesApi(courseGradesData) {
    return request({
        method: 'post',
        url: '/course/grades',
        data: courseGradesData,  // 添加成绩的数据
    });
}

// 删除成绩
export function deleteCourseGradesByIdApi(id) {
    return request({
        method: 'delete',
        url: `/course/grades/${id}`,
    });
}

// 更新成绩
export function editCourseGradesByIdApi(courseGradesData) {
    return request({
        method: 'put',
        url: '/course/grades',
        data: courseGradesData,  // 更新成绩的数据
    });
}


// 分页条件查询选课列表
export function getCourseSelectionPageByConditionApi(pageNum, pageSize, condition) {
    return request({
        method: 'post',
        url: `/course/selection/page/${pageNum}/${pageSize}`,
        data: condition,  // 查询条件
    });
}

// 添加选课信息
export function addCourseSelectionApi(courseSelectionData) {
    return request({
        method: 'post',
        url: '/course/selection',
        data: courseSelectionData,  // 添加选课信息的数据
    });
}

// 删除选课信息
export function deleteCourseSelectionByIdApi(id) {
    return request({
        method: 'delete',
        url: `/course/selection/${id}`,
    });
}

// 更新选课信息
export function editCourseSelectionByIdApi(courseSelectionData) {
    return request({
        method: 'put',
        url: '/course/selection',
        data: courseSelectionData,  // 更新选课信息的数据
    });
}