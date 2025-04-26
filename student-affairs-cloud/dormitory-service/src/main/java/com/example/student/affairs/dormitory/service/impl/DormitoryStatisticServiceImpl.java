package com.example.student.affairs.dormitory.service.impl;

import com.example.student.affairs.dormitory.mapper.DormitoryDisciplineRecordMapper;
import com.example.student.affairs.dormitory.mapper.DormitoryMapper;
import com.example.student.affairs.dormitory.model.Dormitory;
import com.example.student.affairs.dormitory.model.DormitoryDisciplineRecord;
import com.example.student.affairs.dormitory.service.DormitoryStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2024/12/21 13:53
 */
@Service
@RequiredArgsConstructor
public class DormitoryStatisticServiceImpl implements DormitoryStatisticService {
    private final DormitoryMapper dormitoryMapper;
    private final DormitoryDisciplineRecordMapper dormitoryDisciplineRecordMapper;

    @Override
    public Map<String, Map<String, Integer>> getEcharts4() {
        // 获取当前日期，及过去7天的日期
        LocalDate today = LocalDate.now();
        List<LocalDate> pastWeekDates = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            pastWeekDates.add(today.minusDays(i));
        }
        // 获取所有宿舍信息
        List<Dormitory> dormitories = dormitoryMapper.selectList(null);
        // 创建返回结果的 Map
        Map<String, Map<String, Integer>> result = new HashMap<>();
        for (Dormitory dormitory : dormitories) {
            // 获取该宿舍过去7天内的违纪记录
            LocalDate endDatePlusOne = pastWeekDates.get(6).plusDays(1);
            List<DormitoryDisciplineRecord> records =
                    dormitoryDisciplineRecordMapper.getDisciplineRecordsByDormitoryAndDateRange(
                    dormitory.getId(), endDatePlusOne, pastWeekDates.get(0)
            );
            // 将违纪记录按日期统计
            Map<String, Integer> dateToDisciplineCount = new HashMap<>();
            for (LocalDate date : pastWeekDates) {
                dateToDisciplineCount.put(date.toString(), 0);
            }
            for (DormitoryDisciplineRecord record : records) {
                String dateStr = record.getIncidentDate().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (dateToDisciplineCount.containsKey(dateStr)) {
                    dateToDisciplineCount.put(dateStr, dateToDisciplineCount.get(dateStr) + 1);
                }
            }
            // 生成 key：楼栋名称+宿舍名称
            String key = dormitory.getBuildingName() + dormitory.getDormitoryName();
            result.put(key, dateToDisciplineCount);
        }
        return result;
    }
}
