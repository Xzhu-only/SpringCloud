package com.example.student.affairs.course.mapper;

import com.example.student.affairs.course.model.CourseGrades;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author zhanh
* @description 针对表【course_grades(成绩表)】的数据库操作Mapper
* @createDate 2024-12-19 19:02:20
* @Entity com.example.student.affairs.course.model.CourseGrades
*/
public interface CourseGradesMapper extends BaseMapper<CourseGrades> {
    // 查询每门课程的成绩统计（平均成绩、最大成绩、最小成绩）
    @Select("SELECT c.course_name AS courseName, " +
            "AVG(g.grade) AS average, " +
            "MAX(g.grade) AS maxGrade, " +
            "MIN(g.grade) AS minGrade " +
            "FROM course_grades g " +
            "JOIN course_selection s ON g.selection_id = s.id " +
            "JOIN course c ON s.course_id = c.id " +
            "GROUP BY c.course_name")
    List<Map<String, Object>> getCourseGradesStatistics();

    @Select("SELECT grade FROM course_grades")
    List<CourseGrades> getAllGrades();
}




