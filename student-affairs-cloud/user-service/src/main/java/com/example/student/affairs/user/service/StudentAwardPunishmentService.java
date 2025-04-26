package com.example.student.affairs.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.user.model.StatisticMap;
import com.example.student.affairs.user.model.StudentAwardPunishment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhanh
* @description 针对表【student_award_punishment(奖惩表)】的数据库操作Service
* @createDate 2024-12-19 19:03:46
*/
public interface StudentAwardPunishmentService extends IService<StudentAwardPunishment> {

    Page<StudentAwardPunishment> getPageByCondition(Page<StudentAwardPunishment> studentAwardPunishmentPage, StudentAwardPunishment condition);

    boolean addStudentAwardPunishment(StudentAwardPunishment studentAwardPunishment);

    boolean deleteStudentAwardPunishmentById(Integer id);

    boolean editStudentAwardPunishmentById(StudentAwardPunishment studentAwardPunishment);

    List<StatisticMap> getEcharts5();
}
