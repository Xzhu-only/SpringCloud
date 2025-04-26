package com.example.student.affairs.dormitory.mapper;

import com.example.student.affairs.dormitory.model.DormitoryDisciplineRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
* @author zhanh
* @description 针对表【dormitory_discipline_record(宿舍违纪记录表)】的数据库操作Mapper
* @createDate 2024-12-19 19:03:07
* @Entity com.example.student.affairs.dormitory.model.DormitoryDisciplineRecord
*/
public interface DormitoryDisciplineRecordMapper extends BaseMapper<DormitoryDisciplineRecord> {
    @Select("SELECT * FROM dormitory_discipline_record " +
            "WHERE dormitory_id = #{dormitoryId} AND incident_date BETWEEN #{startDate} AND #{endDate}")
    List<DormitoryDisciplineRecord> getDisciplineRecordsByDormitoryAndDateRange(
            @Param("dormitoryId") Integer dormitoryId,
            @Param("endDate") LocalDate endDate,
            @Param("startDate") LocalDate startDate);
}




