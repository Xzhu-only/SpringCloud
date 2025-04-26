package com.example.student.affairs.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.course.model.CourseSelection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhanh
* @description 针对表【course_selection(选课表)】的数据库操作Service
* @createDate 2024-12-19 19:02:20
*/
public interface CourseSelectionService extends IService<CourseSelection> {
    Page<CourseSelection> getPageByCondition(Page<CourseSelection> courseSelectionPage, CourseSelection condition);

    boolean addCourseSelection(CourseSelection course);

    boolean deleteCourseSelectionById(Integer id);

    boolean editCourseSelectionById(CourseSelection course);

    List<CourseSelection> getList();
}
