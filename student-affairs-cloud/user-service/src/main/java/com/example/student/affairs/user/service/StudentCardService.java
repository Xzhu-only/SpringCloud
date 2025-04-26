package com.example.student.affairs.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.user.model.StudentCard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【student_card(学生证表)】的数据库操作Service
* @createDate 2024-12-19 19:03:46
*/
public interface StudentCardService extends IService<StudentCard> {

    Page<StudentCard> getPageByCondition(Page<StudentCard> studentCardPage, StudentCard condition);

    boolean addStudentCard(StudentCard studentCard);

    boolean deleteStudentCardById(Integer id);

    boolean editStudentCardById(StudentCard studentCard);
}
