package com.example.student.affairs.course.service.impl;

import com.example.student.affairs.course.mapper.CourseGradesMapper;
import com.example.student.affairs.course.mapper.CourseMapper;
import com.example.student.affairs.course.mapper.CourseSelectionMapper;
import com.example.student.affairs.course.model.CourseGrades;
import com.example.student.affairs.course.model.StatisticMap;
import com.example.student.affairs.course.model.StatisticMapTwo;
import com.example.student.affairs.course.service.CourseStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @date 2024/12/21 12:01
 */
@Service
@RequiredArgsConstructor
public class CourseStatisticServiceImpl implements CourseStatisticService {
    private final CourseMapper courseMapper;
    private final CourseGradesMapper courseGradesMapper;
    private final CourseSelectionMapper courseSelectionMapper;

    @Override
    public Map<String, List<List<Double>>> getEcharts1() {
        // 查询每门课程的成绩统计（平均成绩、最大成绩、最小成绩）
        List<Map<String, Object>> courseGrades = courseGradesMapper.getCourseGradesStatistics();
        // 创建一个结果 Map，用来存储每门课程的统计数据
        Map<String, List<List<Double>>> result = new HashMap<>();
        // 创建 DecimalFormat 实例，设置格式为两位小数
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        // 遍历查询结果，处理每一门课程的成绩统计
        for (Map<String, Object> record : courseGrades) {
            String courseName = (String) record.get("courseName");
            Double average = (Double) record.get("average");
            Double maxGrade = (Double) record.get("maxGrade");
            Double minGrade = (Double) record.get("minGrade");
            // 格式化平均成绩，确保保留两位小数
            average = Double.valueOf(decimalFormat.format(average));
            // 将各项统计数据（平均成绩、最大成绩、最小成绩）存入 List
            List<Double> stats = Arrays.asList(average, maxGrade, minGrade);
            // 将结果存入 Map
            result.put(courseName, Collections.singletonList(stats));
        }
        return result;
    }

    @Override
    public List<StatisticMap> getEcharts2() {
        return courseSelectionMapper.getCourseSelectionCounts();
    }

    @Override
    public List<StatisticMap> getEcharts3() {
        List<CourseGrades> grades = courseGradesMapper.getAllGrades();
        int failCount = 0;
        int goodCount = 0;
        int excellentCount = 0;
        //进行分组聚类统计
        for (CourseGrades grade : grades) {
            double score = grade.getGrade();
            if (score < 60) {
                failCount++;
            } else if (score >= 60 && score <= 80) {
                goodCount++;
            } else {
                excellentCount++;
            }
        }
        return Arrays.asList(
                new StatisticMap(failCount, "不及格"),
                new StatisticMap(goodCount, "良好"),
                new StatisticMap(excellentCount, "优秀")
        );
    }

    @Override
    public List<StatisticMapTwo> getEcharts6() {
        List<StatisticMapTwo> list = courseSelectionMapper.getCourseSelectionCountsTwo();
        for (StatisticMapTwo statisticMapTwo : list) {
            statisticMapTwo.setMax(18);
        }
        return list;
    }
}
