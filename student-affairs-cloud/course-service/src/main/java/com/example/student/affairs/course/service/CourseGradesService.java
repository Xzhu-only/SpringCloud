package com.example.student.affairs.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.course.model.CourseGrades;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【course_grades(成绩表)】的数据库操作Service
* @createDate 2024-12-19 19:02:20
*/
public interface CourseGradesService extends IService<CourseGrades> {
    boolean addCourseGrades(CourseGrades course);

    boolean deleteCourseGradesById(Integer id);

    boolean editCourseGradesById(CourseGrades course);
}
