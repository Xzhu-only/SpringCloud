package com.example.student.affairs.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.user.model.StudentFinancialAid;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【student_financial_aid( 学生资助表)】的数据库操作Service
* @createDate 2024-12-19 19:03:46
*/
public interface StudentFinancialAidService extends IService<StudentFinancialAid> {

    Page<StudentFinancialAid> getPageByCondition(Page<StudentFinancialAid> studentFinancialAidPage, StudentFinancialAid condition);

    boolean addStudentFinancialAid(StudentFinancialAid studentFinancialAid);

    boolean deleteStudentFinancialAidById(Integer id);

    boolean editStudentFinancialAidById(StudentFinancialAid studentFinancialAid);
}
