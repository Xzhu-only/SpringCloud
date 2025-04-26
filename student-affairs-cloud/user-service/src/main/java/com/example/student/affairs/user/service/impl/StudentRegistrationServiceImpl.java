package com.example.student.affairs.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.user.model.StudentRegistration;
import com.example.student.affairs.user.service.StudentRegistrationService;
import com.example.student.affairs.user.mapper.StudentRegistrationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author zhanh
* @description 针对表【student_registration(新生预报登记表)】的数据库操作Service实现
* @createDate 2024-12-20 21:26:17
*/
@Service
@RequiredArgsConstructor
public class StudentRegistrationServiceImpl extends ServiceImpl<StudentRegistrationMapper, StudentRegistration>
    implements StudentRegistrationService{
    private final StudentRegistrationMapper studentRegistrationMapper;

    @Override
    public Page<StudentRegistration> getPageByCondition(Page<StudentRegistration> studentRegistrationPage, StudentRegistration condition) {
        QueryWrapper<StudentRegistration> queryWrapper = new QueryWrapper<>();
        if (condition.getUserId() != null) {
            queryWrapper.lambda().eq(StudentRegistration::getUserId, condition.getUserId());
        }
        queryWrapper.lambda().orderByDesc(StudentRegistration::getId);
        Page<StudentRegistration> page = studentRegistrationMapper.selectPage(studentRegistrationPage, queryWrapper);
        return page;
    }

    @Override
    public boolean addStudentRegistration(StudentRegistration studentRegistration) {
        studentRegistration.setCreateTime(new Date());
        return studentRegistrationMapper.insert(studentRegistration) > 0;
    }

    @Override
    public boolean deleteStudentRegistrationById(Integer id) {
        return studentRegistrationMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editStudentRegistrationById(StudentRegistration studentRegistration) {
        return studentRegistrationMapper.updateById(studentRegistration) > 0;
    }
}




