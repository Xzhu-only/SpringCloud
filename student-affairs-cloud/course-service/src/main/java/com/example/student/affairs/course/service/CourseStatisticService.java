package com.example.student.affairs.course.service;

import com.example.student.affairs.course.model.StatisticMap;
import com.example.student.affairs.course.model.StatisticMapTwo;

import java.util.List;
import java.util.Map;

/**
 * @date 2024/12/21 12:01
 */
public interface CourseStatisticService {
    Map<String, List<List<Double>>> getEcharts1();

    List<StatisticMap> getEcharts2();

    List<StatisticMap> getEcharts3();

    List<StatisticMapTwo> getEcharts6();
}
