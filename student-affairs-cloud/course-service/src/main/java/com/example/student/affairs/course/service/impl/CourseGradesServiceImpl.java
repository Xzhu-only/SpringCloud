package com.example.student.affairs.course.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.course.mapper.CourseSelectionMapper;
import com.example.student.affairs.course.model.CourseGrades;
import com.example.student.affairs.course.model.CourseSelection;
import com.example.student.affairs.course.service.CourseGradesService;
import com.example.student.affairs.course.mapper.CourseGradesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author zhanh
* @description 针对表【course_grades(成绩表)】的数据库操作Service实现
* @createDate 2024-12-19 19:02:20
*/
@Service
@RequiredArgsConstructor
public class CourseGradesServiceImpl extends ServiceImpl<CourseGradesMapper, CourseGrades>
    implements CourseGradesService{
    private final CourseGradesMapper courseGradesMapper;

    @Override
    public boolean addCourseGrades(CourseGrades courseGrades) {
        //判断是否已经录入过该选课信息的成绩信息
        QueryWrapper<CourseGrades> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CourseGrades::getSelectionId, courseGrades.getSelectionId());
        List<CourseGrades> gradesList = courseGradesMapper.selectList(queryWrapper);
        if (!gradesList.isEmpty()) {
            throw new BusinessException("成绩录入失败，您已经录入过该选课信息了！");
        }
        courseGrades.setGradeDate(new Date());
        return courseGradesMapper.insert(courseGrades) > 0;
    }

    @Override
    public boolean deleteCourseGradesById(Integer id) {
        return courseGradesMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editCourseGradesById(CourseGrades courseGrades) {
        return courseGradesMapper.updateById(courseGrades) > 0;
    }
}




