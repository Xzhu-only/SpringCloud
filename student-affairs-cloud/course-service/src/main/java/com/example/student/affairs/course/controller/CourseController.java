package com.example.student.affairs.course.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.course.model.Course;
import com.example.student.affairs.course.model.dto.CourseDTO;
import com.example.student.affairs.course.service.CourseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2024/12/19 19:06
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    /**
     * 分页条件查询课程列表
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody Course condition) {
        Page<Course> voPage = courseService.getPageByCondition(new Page<Course>(pageNum, pageSize), condition);
        return Result.success(voPage);
    }
    
    /**
     * 查询课程列表
     */
    @GetMapping("/list")
    public Result getList() {
        List<Course> list = courseService.getList();
        return Result.success(list);
    }

    /**
     * 添加课程
     */
    @PostMapping
    public Result addCourse(@RequestBody @Validated(value = AddGroup.class) CourseDTO courseDTO) {
        Course course = courseDTO.dtoToDo();
        return courseService.addCourse(course) ? Result.success("添加课程成功") : Result.error("添加课程失败");
    }

    /**
     * 删除课程
     */
    @DeleteMapping("/{id}")
    public Result deleteCourseById(@PathVariable("id") Integer id) {
        return courseService.deleteCourseById(id) ? Result.success("删除课程成功") : Result.error("删除课程失败");
    }

    /**
     * 修改课程
     */
    @PutMapping
    public Result editCourseById(@RequestBody @Validated(value = UpdateGroup.class) CourseDTO courseDTO) {
        Course course = courseDTO.dtoToDo();
        return courseService.editCourseById(course) ? Result.success("更新课程成功") : Result.error("更新课程失败");
    }
}
