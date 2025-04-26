package com.example.student.affairs.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.feign.client.CourseSelectionClient;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.model.CourseSelection;
import com.example.student.affairs.user.mapper.UserMapper;
import com.example.student.affairs.user.model.StatisticMap;
import com.example.student.affairs.user.model.StudentAwardPunishment;
import com.example.student.affairs.user.model.StudentCard;
import com.example.student.affairs.user.model.User;
import com.example.student.affairs.user.service.StudentAwardPunishmentService;
import com.example.student.affairs.user.mapper.StudentAwardPunishmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【student_award_punishment(奖惩表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:46
*/
@Service
@RequiredArgsConstructor
public class StudentAwardPunishmentServiceImpl extends ServiceImpl<StudentAwardPunishmentMapper, StudentAwardPunishment>
    implements StudentAwardPunishmentService{
    private final StudentAwardPunishmentMapper studentAwardPunishmentMapper;
    private final UserMapper userMapper;
    private final CourseSelectionClient courseSelectionClient;

    @Override
    public Page<StudentAwardPunishment> getPageByCondition(Page<StudentAwardPunishment> studentAwardPunishmentPage, StudentAwardPunishment condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userMapper.selectUserById(userId);
        QueryWrapper<StudentAwardPunishment> queryWrapper = new QueryWrapper<>();
        if (condition.getStudentId() != null) {
            queryWrapper.lambda().eq(StudentAwardPunishment::getStudentId, condition.getStudentId());
        } else if (user.getRole() == 2) {
            //如果是教师角色用户，则只查询自己负责的课程选课记录里的学生奖惩信息
            List<Integer> studentIds = courseSelectionClient.getList()
                    .stream().map(CourseSelection::getStudentId).distinct().collect(Collectors.toList());
            if (!studentIds.isEmpty()) {
                queryWrapper.lambda().in(StudentAwardPunishment::getStudentId, studentIds);
            }
        }
        if (StrUtil.isNotBlank(condition.getType())) {
            queryWrapper.lambda().like(StudentAwardPunishment::getType, condition.getType());
        }
        queryWrapper.lambda().orderByDesc(StudentAwardPunishment::getId);
        Page<StudentAwardPunishment> page = studentAwardPunishmentMapper.selectPage(studentAwardPunishmentPage, queryWrapper);
        this.fillAwardDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addStudentAwardPunishment(StudentAwardPunishment studentAwardPunishment) {
        studentAwardPunishment.setCreateTime(new Date());
        return studentAwardPunishmentMapper.insert(studentAwardPunishment) > 0;
    }

    @Override
    public boolean deleteStudentAwardPunishmentById(Integer id) {
        return studentAwardPunishmentMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editStudentAwardPunishmentById(StudentAwardPunishment studentAwardPunishment) {
        return studentAwardPunishmentMapper.updateById(studentAwardPunishment) > 0;
    }

    @Override
    public List<StatisticMap> getEcharts5() {
        List<StudentAwardPunishment> awardPunishmentList = studentAwardPunishmentMapper.selectList(null);
        List<StudentAwardPunishment> filterList1 = awardPunishmentList.stream()
                .filter(item -> "奖励".equals(item.getType())).collect(Collectors.toList());
        List<StudentAwardPunishment> filterList2 = awardPunishmentList.stream()
                .filter(item -> "惩罚".equals(item.getType())).collect(Collectors.toList());
        return Arrays.asList(
                new StatisticMap(filterList1.size(), "奖励"),
                new StatisticMap(filterList2.size(), "惩罚")
        );
    }

    private void fillAwardDetail(List<StudentAwardPunishment> list) {
        for (StudentAwardPunishment award : list) {
            User user = userMapper.selectUserById(award.getStudentId());
            award.setStudentName(user.getUsername());
        }
    }
}




