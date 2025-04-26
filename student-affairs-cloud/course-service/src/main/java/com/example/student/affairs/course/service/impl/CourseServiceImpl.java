package com.example.student.affairs.course.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.course.mapper.CourseSelectionMapper;
import com.example.student.affairs.course.model.Course;
import com.example.student.affairs.course.service.CourseService;
import com.example.student.affairs.course.mapper.CourseMapper;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author zhanh
* @description 针对表【course(课程表)】的数据库操作Service实现
* @createDate 2024-12-19 19:02:20
*/
@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{
    private final CourseMapper courseMapper;
    private final UserClient userClient;

    @Override
    public Page<Course> getPageByCondition(Page<Course> coursePage, Course condition) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(condition.getCourseName())) {
            queryWrapper.lambda().like(Course::getCourseName, condition.getCourseName());
        }
        if (StrUtil.isNotBlank(condition.getCourseCode())) {
            queryWrapper.lambda().like(Course::getCourseCode, condition.getCourseCode());
        }
        if (condition.getTeacherId() != null) {
            queryWrapper.lambda().eq(Course::getTeacherId, condition.getTeacherId());
        }
        queryWrapper.lambda().orderByDesc(Course::getId);
        Page<Course> page = courseMapper.selectPage(coursePage, queryWrapper);
        this.fillCourseList(page.getRecords());
        return page;
    }

    @Override
    public List<Course> getList() {
        Integer userId = ThreadLocalUtil.get();
        User user = userClient.getUserById(userId);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (user.getRole() == 2) {
            queryWrapper.lambda().eq(Course::getTeacherId, userId);
        }
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean addCourse(Course course) {
        //判断是否有重名课程
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Course::getCourseName, course.getCourseName());
        List<Course> courseList = courseMapper.selectList(queryWrapper);
        if (!courseList.isEmpty()) {
            throw new BusinessException("添加失败，存在重名课程！");
        }
        Integer userId = ThreadLocalUtil.get();
        if (course.getTeacherId() == null) {
            course.setTeacherId(userId);
        }
        course.setCreateTime(new Date());
        return courseMapper.insert(course) > 0;
    }

    @Override
    public boolean deleteCourseById(Integer id) {
        return courseMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editCourseById(Course course) {
        return courseMapper.updateById(course) > 0;
    }

    private void fillCourseList(List<Course> list) {
        for (Course course : list) {
            User teacher = userClient.getUserById(course.getTeacherId());
            course.setTeacherName(teacher.getUsername());
        }
    }
}




