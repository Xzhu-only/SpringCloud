package com.example.student.affairs.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.user.model.StudentRegistration;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【student_registration(新生预报登记表)】的数据库操作Service
* @createDate 2024-12-20 21:26:17
*/
public interface StudentRegistrationService extends IService<StudentRegistration> {

    Page<StudentRegistration> getPageByCondition(Page<StudentRegistration> studentRegistrationPage, StudentRegistration condition);

    boolean addStudentRegistration(StudentRegistration studentRegistration);

    boolean deleteStudentRegistrationById(Integer id);

    boolean editStudentRegistrationById(StudentRegistration studentRegistration);
}
