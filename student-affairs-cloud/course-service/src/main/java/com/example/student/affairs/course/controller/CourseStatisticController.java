package com.example.student.affairs.course.controller;

import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.course.model.StatisticMap;
import com.example.student.affairs.course.model.StatisticMapTwo;
import com.example.student.affairs.course.service.CourseStatisticService;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 成绩统计接口
 * @date 2024/12/21 11:25
 */
@RestController
@RequestMapping("/course/statistic")
public class CourseStatisticController {
    @Resource
    private CourseStatisticService courseStatisticService;

    /**
     * 描述性统计计算每门课程的成绩平均值、最大、最小值
     */
    @GetMapping("/getEcharts1")
    public Result getEcharts1() {
        Map<String, List<List<Double>>> echarts1 = courseStatisticService.getEcharts1();
        return Result.success(echarts1);
    }

    /**
     * 统计每门课程的选课数量进行排序
     */
    @GetMapping("/getEcharts2")
    public Result getEcharts2() {
        List<StatisticMap> echarts2 = courseStatisticService.getEcharts2();
        return Result.success(echarts2);
    }

    /**
     * 统计每门课程的选课数量进行排序
     */
    @GetMapping("/getEcharts3")
    public Result getEcharts3() {
        List<StatisticMap> echarts3 = courseStatisticService.getEcharts3();
        return Result.success(echarts3);
    }

    /**
     * 统计用户选课偏好
     */
    @GetMapping("/getEcharts6")
    public Result getEcharts6() {
        List<StatisticMapTwo> echarts6 = courseStatisticService.getEcharts6();
        return Result.success(echarts6);
    }
}
