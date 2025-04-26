package com.example.student.affairs.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.feign.client.CourseSelectionClient;
import com.example.student.affairs.feign.model.CourseSelection;
import com.example.student.affairs.user.mapper.CampusCardMapper;
import com.example.student.affairs.user.mapper.StudentCardMapper;
import com.example.student.affairs.user.mapper.UserMapper;
import com.example.student.affairs.user.model.*;
import com.example.student.affairs.user.model.StudentFinancialAid;
import com.example.student.affairs.user.service.StudentFinancialAidService;
import com.example.student.affairs.user.mapper.StudentFinancialAidMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【student_financial_aid( 学生资助表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:46
*/
@Service
@RequiredArgsConstructor
public class StudentFinancialAidServiceImpl extends ServiceImpl<StudentFinancialAidMapper, StudentFinancialAid>
    implements StudentFinancialAidService{
    private final StudentFinancialAidMapper studentFinancialAidMapper;
    private final UserMapper userMapper;
    private final CampusCardMapper campusCardMapper;
    private final CourseSelectionClient courseSelectionClient;

    @Override
    public Page<StudentFinancialAid> getPageByCondition(Page<StudentFinancialAid> studentFinancialAidPage, StudentFinancialAid condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userMapper.selectUserById(userId);
        QueryWrapper<StudentFinancialAid> queryWrapper = new QueryWrapper<>();
        if (condition.getStudentId() != null) {
            queryWrapper.lambda().eq(StudentFinancialAid::getStudentId, condition.getStudentId());
        } else if (user.getRole() == 2) {
            //如果是教师角色用户，则只查询自己负责的课程选课记录里的学生资助信息
            List<Integer> studentIds = courseSelectionClient.getList()
                    .stream().map(CourseSelection::getStudentId).distinct().collect(Collectors.toList());
            if (!studentIds.isEmpty()) {
                queryWrapper.lambda().in(StudentFinancialAid::getStudentId, studentIds);
            }
        }
        if (StrUtil.isNotBlank(condition.getAidType())) {
            queryWrapper.lambda().eq(StudentFinancialAid::getAidType, condition.getAidType());
        }
        if (StrUtil.isNotBlank(condition.getState())) {
            queryWrapper.lambda().eq(StudentFinancialAid::getState, condition.getState());
        }
        queryWrapper.lambda().orderByDesc(StudentFinancialAid::getId);
        Page<StudentFinancialAid> page = studentFinancialAidMapper.selectPage(studentFinancialAidPage, queryWrapper);
        this.fillDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addStudentFinancialAid(StudentFinancialAid studentFinancialAid) {
        studentFinancialAid.setCreateTime(new Date());
        return studentFinancialAidMapper.insert(studentFinancialAid) > 0;
    }

    @Override
    public boolean deleteStudentFinancialAidById(Integer id) {
        return studentFinancialAidMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editStudentFinancialAidById(StudentFinancialAid studentFinancialAid) {
        //判断修改后的资助状态是否是已领取，是则将金额充值到校园卡
        StudentFinancialAid temp = studentFinancialAidMapper.selectById(studentFinancialAid.getId());
        if (temp == null) {
            throw new BusinessException("修改失败，资助信息不存在！");
        }
        if (studentFinancialAid.getState().equals("已领取")) {
            //获取资助学生的校园卡
            CampusCard card = campusCardMapper.selectByStudentId(temp.getStudentId());
            if (card == null) {
                throw new BusinessException("领取失败，资助学生还未办理校园卡！");
            }
            card.setBalance(card.getBalance() + studentFinancialAid.getAidAmount());
            campusCardMapper.updateById(card);
        }
        return studentFinancialAidMapper.updateById(studentFinancialAid) > 0;
    }
    private void fillDetail(List<StudentFinancialAid> list) {
        for (StudentFinancialAid aid : list) {
            User user = userMapper.selectUserById(aid.getStudentId());
            aid.setStudentName(user.getUsername());
        }
    }
}




