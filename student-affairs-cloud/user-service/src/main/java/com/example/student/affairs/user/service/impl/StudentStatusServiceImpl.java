package com.example.student.affairs.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.user.model.StudentStatus;
import com.example.student.affairs.user.service.StudentStatusService;
import com.example.student.affairs.user.mapper.StudentStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author zhanh
* @description 针对表【student_status(学籍表)】的数据库操作Service实现
* @createDate 2024-12-21 09:59:11
*/
@Service
@RequiredArgsConstructor
public class StudentStatusServiceImpl extends ServiceImpl<StudentStatusMapper, StudentStatus>
    implements StudentStatusService{
    private final StudentStatusMapper studentStatusMapper;

    @Override
    public boolean addStudentStatus(StudentStatus studentStatus) {
        //判断是否已经添加过学籍了
        QueryWrapper<StudentStatus> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StudentStatus::getStudentId, studentStatus.getStudentId());
        List<StudentStatus> studentStatusList = studentStatusMapper.selectList(queryWrapper);
        if (!studentStatusList.isEmpty()) {
            throw new BusinessException("添加学籍失败，已经添加过了！");
        }
        studentStatus.setCreateTime(new Date());
        return studentStatusMapper.insert(studentStatus) > 0;
    }

    @Override
    public boolean deleteStudentStatusById(Integer id) {
        return studentStatusMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editStudentStatusById(StudentStatus studentStatus) {
        return studentStatusMapper.updateById(studentStatus) > 0;
    }

    @Override
    public StudentStatus getStudentStatusByStudentId(Integer userId) {
        QueryWrapper<StudentStatus> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StudentStatus::getStudentId, userId);
        StudentStatus studentStatus = studentStatusMapper.selectOne(queryWrapper);
        return studentStatus;
    }
}




