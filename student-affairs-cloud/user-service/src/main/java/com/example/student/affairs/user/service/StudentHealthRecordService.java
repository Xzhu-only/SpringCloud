package com.example.student.affairs.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.user.model.StudentHealthRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【student_health_record(学生健康记录表)】的数据库操作Service
* @createDate 2024-12-19 19:03:46
*/
public interface StudentHealthRecordService extends IService<StudentHealthRecord> {

    Page<StudentHealthRecord> getPageByCondition(Page<StudentHealthRecord> studentHealthRecordPage, StudentHealthRecord condition);

    boolean addStudentHealthRecord(StudentHealthRecord studentHealthRecord);

    boolean deleteStudentHealthRecordById(Integer id);

    boolean editStudentHealthRecordById(StudentHealthRecord studentHealthRecord);
}
