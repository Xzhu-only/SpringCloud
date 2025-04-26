package com.example.student.affairs.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.feign.client.CourseSelectionClient;
import com.example.student.affairs.feign.model.CourseSelection;
import com.example.student.affairs.user.mapper.UserMapper;
import com.example.student.affairs.user.model.StudentAwardPunishment;
import com.example.student.affairs.user.model.StudentHealthRecord;
import com.example.student.affairs.user.model.User;
import com.example.student.affairs.user.service.StudentHealthRecordService;
import com.example.student.affairs.user.mapper.StudentHealthRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【student_health_record(学生健康记录表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:46
*/
@Service
@RequiredArgsConstructor
public class StudentHealthRecordServiceImpl extends ServiceImpl<StudentHealthRecordMapper, StudentHealthRecord>
    implements StudentHealthRecordService{
    private final StudentHealthRecordMapper studentHealthRecordMapper;
    private final UserMapper userMapper;
    private final CourseSelectionClient courseSelectionClient;

    @Override
    public Page<StudentHealthRecord> getPageByCondition(Page<StudentHealthRecord> studentHealthRecordPage, StudentHealthRecord condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userMapper.selectUserById(userId);
        QueryWrapper<StudentHealthRecord> queryWrapper = new QueryWrapper<>();
        if (condition.getStudentId() != null) {
            queryWrapper.lambda().eq(StudentHealthRecord::getStudentId, condition.getStudentId());
        } else if (user.getRole() == 2) {
            //如果是教师角色用户，则只查询自己负责的课程选课记录里的学生健康记录信息
            List<Integer> studentIds = courseSelectionClient.getList()
                    .stream().map(CourseSelection::getStudentId).distinct().collect(Collectors.toList());
            if (!studentIds.isEmpty()) {
                queryWrapper.lambda().in(StudentHealthRecord::getStudentId, studentIds);
            }
        }
        queryWrapper.lambda().orderByDesc(StudentHealthRecord::getId);
        Page<StudentHealthRecord> page = studentHealthRecordMapper.selectPage(studentHealthRecordPage, queryWrapper);
        this.fillDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addStudentHealthRecord(StudentHealthRecord studentHealthRecord) {
        studentHealthRecord.setCreateTime(new Date());
        return studentHealthRecordMapper.insert(studentHealthRecord) > 0;
    }

    @Override
    public boolean deleteStudentHealthRecordById(Integer id) {
         return studentHealthRecordMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editStudentHealthRecordById(StudentHealthRecord studentHealthRecord) {
        return studentHealthRecordMapper.updateById(studentHealthRecord) > 0;
    }

    private void fillDetail(List<StudentHealthRecord> list) {
        for (StudentHealthRecord health : list) {
            User user = userMapper.selectUserById(health.getStudentId());
            health.setStudentName(user.getUsername());
        }
    }
}




