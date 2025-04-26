package com.example.student.affairs.course.mapper;

import com.example.student.affairs.course.model.CourseSelection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.student.affairs.course.model.StatisticMap;
import com.example.student.affairs.course.model.StatisticMapTwo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author zhanh
* @description 针对表【course_selection(选课表)】的数据库操作Mapper
* @createDate 2024-12-19 19:02:20
* @Entity com.example.student.affairs.course.model.CourseSelection
*/
public interface CourseSelectionMapper extends BaseMapper<CourseSelection> {
    @Select("SELECT c.course_name AS name, COUNT(s.id) AS value " +
            "FROM course_selection s " +
            "JOIN course c ON s.course_id = c.id " +
            "GROUP BY c.course_name " +
            "ORDER BY value ASC")
    List<StatisticMap> getCourseSelectionCounts();

    @Select("SELECT c.course_name AS name, COUNT(s.id) AS max " +
            "FROM course_selection s " +
            "JOIN course c ON s.course_id = c.id " +
            "GROUP BY c.course_name " +
            "ORDER BY max ASC")
    List<StatisticMapTwo> getCourseSelectionCountsTwo();
}




