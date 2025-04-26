package com.example.student.affairs.dormitory.service;

import java.util.Map;

/**
 * @date 2024/12/21 13:53
 */
public interface DormitoryStatisticService {
    Map<String, Map<String, Integer>> getEcharts4();
}
