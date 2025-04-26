import request from '../util/request.js';

export function getEcharts1Api() {
    return request({
        method: 'get',
        url: `/course/statistic/getEcharts1`,
    });
}

export function getEcharts2Api() {
    return request({
        method: 'get',
        url: `/course/statistic/getEcharts2`,
    });
}

export function getEcharts3Api() {
    return request({
        method: 'get',
        url: `/course/statistic/getEcharts3`,
    });
}

export function getEcharts4Api() {
    return request({
        method: 'get',
        url: `/dormitory/statistic/getEcharts4`,
    });
}

export function getEcharts5Api() {
    return request({
        method: 'get',
        url: `/student/award_punishment/statistic/getEcharts5`,
    });
}

export function getEcharts6Api() {
    return request({
        method: 'get',
        url: `/course/statistic/getEcharts6`,
    });
}