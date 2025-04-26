package com.example.student.affairs.course.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.course.mapper.CourseGradesMapper;
import com.example.student.affairs.course.mapper.CourseMapper;
import com.example.student.affairs.course.model.Course;
import com.example.student.affairs.course.model.CourseGrades;
import com.example.student.affairs.course.model.CourseSelection;
import com.example.student.affairs.course.service.CourseSelectionService;
import com.example.student.affairs.course.mapper.CourseSelectionMapper;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【course_selection(选课表)】的数据库操作Service实现
* @createDate 2024-12-19 19:02:20
*/
@Service
@RequiredArgsConstructor
public class CourseSelectionServiceImpl extends ServiceImpl<CourseSelectionMapper, CourseSelection>
    implements CourseSelectionService{
    private final CourseSelectionMapper courseSelectionMapper;
    private final CourseGradesMapper courseGradesMapper;
    private final CourseMapper courseMapper;
    private final UserClient userClient;

    @Override
    public Page<CourseSelection> getPageByCondition(Page<CourseSelection> courseSelectionPage, CourseSelection condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userClient.getUserById(userId);
        QueryWrapper<CourseSelection> queryWrapper = new QueryWrapper<>();
        if (condition.getStudentId() != null) {
            queryWrapper.lambda().eq(CourseSelection::getStudentId, condition.getStudentId());
        }
        if (condition.getCourseId() != null) {
            queryWrapper.lambda().eq(CourseSelection::getCourseId, condition.getCourseId());
        } else if (user.getRole() == 2){
            //如果是教师角色，则只查询自己负责的课程
            QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
            courseQueryWrapper.lambda().eq(Course::getTeacherId, userId);
            List<Integer> courseIds = courseMapper.selectList(courseQueryWrapper)
                    .stream().map(Course::getId).collect(Collectors.toList());
            if (!courseIds.isEmpty()) {
                queryWrapper.lambda().in(CourseSelection::getCourseId, courseIds);
            } else {
                return courseSelectionPage;
            }
        }
        if (StrUtil.isNotBlank(condition.getSemester())) {
            queryWrapper.lambda().eq(CourseSelection::getSemester, condition.getSemester());
        }
        queryWrapper.lambda().orderByDesc(CourseSelection::getId);
        Page<CourseSelection> page = courseSelectionMapper.selectPage(courseSelectionPage, queryWrapper);
        this.fillCourseSelectionList(page.getRecords());
        return page;
    }

    @Override
    public boolean addCourseSelection(CourseSelection courseSelection) {
        //判断是否已经选过该课程了
        QueryWrapper<CourseSelection> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CourseSelection::getStudentId, courseSelection.getStudentId());
        queryWrapper.lambda().eq(CourseSelection::getCourseId, courseSelection.getCourseId());
        queryWrapper.lambda().eq(CourseSelection::getSemester, courseSelection.getSemester());
        List<CourseSelection> selectionList = courseSelectionMapper.selectList(queryWrapper);
        if (!selectionList.isEmpty()) {
            throw new BusinessException("选课失败，该学期您已经选过该课程了！");
        }
        courseSelection.setSelectionDate(new Date());
        return courseSelectionMapper.insert(courseSelection) > 0;
    }

    @Override
    public boolean deleteCourseSelectionById(Integer id) {
        return courseSelectionMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editCourseSelectionById(CourseSelection courseSelection) {
        return courseSelectionMapper.updateById(courseSelection) > 0;
    }

    @Override
    public List<CourseSelection> getList() {
        //查询登录用户信息
        Integer userId = ThreadLocalUtil.get();
        User user = userClient.getUserById(userId);
        //判断是否是教师角色，是则查询教师负责的课程选课记录
        QueryWrapper<CourseSelection> queryWrapper = new QueryWrapper<>();
        if (user.getRole() == 2) {
            QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
            courseQueryWrapper.lambda().eq(Course::getTeacherId, userId);
            List<Integer> courseIds = courseMapper.selectList(courseQueryWrapper)
                    .stream().map(Course::getId).distinct().collect(Collectors.toList());
            if (!courseIds.isEmpty()) {
                queryWrapper.lambda().in(CourseSelection::getCourseId, courseIds);
            }
        }
        return courseSelectionMapper.selectList(queryWrapper);
    }

    private void fillCourseSelectionList(List<CourseSelection> list) {
        if (list.isEmpty()) {
            return;
        }
        for (CourseSelection selection : list) {
            User student = userClient.getUserById(selection.getStudentId());
            selection.setStudentName(student.getUsername());
            Course course = courseMapper.selectById(selection.getCourseId());
            selection.setCourse(course);
            QueryWrapper<CourseGrades> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(CourseGrades::getSelectionId, selection.getId());
            CourseGrades courseGrades = courseGradesMapper.selectOne(queryWrapper);
            selection.setCourseGrades(courseGrades);
        }
    }
}




