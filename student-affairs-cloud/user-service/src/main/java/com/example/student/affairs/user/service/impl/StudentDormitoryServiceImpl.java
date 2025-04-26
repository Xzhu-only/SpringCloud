package com.example.student.affairs.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.feign.client.CourseSelectionClient;
import com.example.student.affairs.feign.client.DormitoryClient;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.model.CourseSelection;
import com.example.student.affairs.feign.model.Dormitory;
import com.example.student.affairs.user.mapper.UserMapper;
import com.example.student.affairs.user.model.StudentAwardPunishment;
import com.example.student.affairs.user.model.StudentDormitory;
import com.example.student.affairs.user.model.User;
import com.example.student.affairs.user.service.StudentDormitoryService;
import com.example.student.affairs.user.mapper.StudentDormitoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【student_dormitory( 学生宿舍表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:46
*/
@Service
@RequiredArgsConstructor
public class StudentDormitoryServiceImpl extends ServiceImpl<StudentDormitoryMapper, StudentDormitory>
    implements StudentDormitoryService{
    private final StudentDormitoryMapper studentDormitoryMapper;
    private final UserMapper userMapper;
    private final DormitoryClient dormitoryClient;
    private final CourseSelectionClient courseSelectionClient;

    @Override
    public Page<StudentDormitory> getPageByCondition(Page<StudentDormitory> studentDormitoryPage, StudentDormitory condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userMapper.selectUserById(userId);
        QueryWrapper<StudentDormitory> queryWrapper = new QueryWrapper<>();
        if (condition.getStudentId() != null) {
            queryWrapper.lambda().eq(StudentDormitory::getStudentId, condition.getStudentId());
        } else if (user.getRole() == 2) {
            //如果是教师角色用户，则只查询自己负责的课程选课记录里的学生住宿信息
            List<Integer> studentIds = courseSelectionClient.getList()
                    .stream().map(CourseSelection::getStudentId).distinct().collect(Collectors.toList());
            if (!studentIds.isEmpty()) {
                queryWrapper.lambda().in(StudentDormitory::getStudentId, studentIds);
            }
        }
        if (condition.getDormitoryId() != null) {
            queryWrapper.lambda().eq(StudentDormitory::getDormitoryId, condition.getDormitoryId());
        }
        queryWrapper.lambda().orderByDesc(StudentDormitory::getId);
        Page<StudentDormitory> page = studentDormitoryMapper.selectPage(studentDormitoryPage, queryWrapper);
        this.fillDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addStudentDormitory(StudentDormitory studentDormitory) {
        //判断是否已经选过该宿舍了
        QueryWrapper<StudentDormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StudentDormitory::getStudentId, studentDormitory.getStudentId());
        List<StudentDormitory> studentDormitoryList = studentDormitoryMapper.selectList(queryWrapper);
        if (!studentDormitoryList.isEmpty()) {
            throw new BusinessException("办理宿舍入住失败，您已经入住宿舍了！");
        }
        //改变宿舍入住人数
        Dormitory dormitory = dormitoryClient.getDormitoryById(studentDormitory.getDormitoryId());
        dormitory.setCurrentOccupancy(dormitory.getCurrentOccupancy() + 1);
        dormitoryClient.updateDormitoryDeign(dormitory);
        studentDormitory.setMoveInDate(new Date());
        Date expirationDate = addYearsToDate(new Date(), 4);
        studentDormitory.setMoveOutDate(expirationDate);
        return studentDormitoryMapper.insert(studentDormitory) > 0;
    }

    @Override
    public boolean deleteStudentDormitoryById(Integer id) {
        StudentDormitory studentDormitory = studentDormitoryMapper.selectById(id);
        //减去对应宿舍入住人数
        Dormitory dormitory = dormitoryClient.getDormitoryById(studentDormitory.getDormitoryId());
        dormitory.setCurrentOccupancy(dormitory.getCurrentOccupancy() - 1);
        dormitoryClient.updateDormitoryDeign(dormitory);
        return studentDormitoryMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editStudentDormitoryById(StudentDormitory studentDormitory) {
        return studentDormitoryMapper.updateById(studentDormitory) > 0;
    }

    private void fillDetail(List<StudentDormitory> list) {
        for (StudentDormitory studentDormitory : list) {
            User student = userMapper.selectUserById(studentDormitory.getStudentId());
            Dormitory dormitory = dormitoryClient.getDormitoryById(studentDormitory.getDormitoryId());
            studentDormitory.setStudentName(student.getUsername());
            studentDormitory.setDormitory(dormitory);
        }
    }

    // 给定日期增加指定年数的工具方法
    private Date addYearsToDate(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }
}




