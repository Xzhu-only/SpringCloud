package com.example.student.affairs.user.service;

import com.example.student.affairs.user.model.StudentStatus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【student_status(学籍表)】的数据库操作Service
* @createDate 2024-12-21 09:59:11
*/
public interface StudentStatusService extends IService<StudentStatus> {

    boolean addStudentStatus(StudentStatus studentStatus);

    boolean deleteStudentStatusById(Integer id);

    boolean editStudentStatusById(StudentStatus studentStatus);

    StudentStatus getStudentStatusByStudentId(Integer userId);
}
