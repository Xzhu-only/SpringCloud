package com.example.student.affairs.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.user.model.StudentDormitory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【student_dormitory( 学生宿舍表)】的数据库操作Service
* @createDate 2024-12-19 19:03:46
*/
public interface StudentDormitoryService extends IService<StudentDormitory> {

    Page<StudentDormitory> getPageByCondition(Page<StudentDormitory> studentDormitoryPage, StudentDormitory condition);

    boolean addStudentDormitory(StudentDormitory studentDormitory);

    boolean deleteStudentDormitoryById(Integer id);

    boolean editStudentDormitoryById(StudentDormitory studentDormitory);
}
