package com.example.student.affairs.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.course.model.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhanh
* @description 针对表【course(课程表)】的数据库操作Service
* @createDate 2024-12-19 19:02:20
*/
public interface CourseService extends IService<Course> {

    Page<Course> getPageByCondition(Page<Course> coursePage, Course condition);

    List<Course> getList();

    boolean addCourse(Course course);

    boolean deleteCourseById(Integer id);

    boolean editCourseById(Course course);
}
