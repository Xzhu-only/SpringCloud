package com.example.student.affairs.dormitory.controller;

import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.dormitory.service.DormitoryStatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @date 2024/12/21 13:52
 */
@RestController
@RequestMapping("/dormitory/statistic")
public class DormitoryStatisticController {
    @Resource
    private DormitoryStatisticService dormitoryStatisticService;

    /**
     * 统计七天内宿舍的违纪记录
     */
    @GetMapping("/getEcharts4")
    public Result getEcharts4() {
        Map<String, Map<String, Integer>> echarts4 = dormitoryStatisticService.getEcharts4();
        return Result.success(echarts4);
    }
}
