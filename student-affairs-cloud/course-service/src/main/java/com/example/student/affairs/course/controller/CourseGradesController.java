package com.example.student.affairs.course.controller;

import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.course.model.CourseGrades;
import com.example.student.affairs.course.model.dto.CourseGradesDTO;
import com.example.student.affairs.course.service.CourseGradesService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @date 2024/12/19 19:06
 */
@RestController
@RequestMapping("/course/grades")
public class CourseGradesController {
    @Resource
    private CourseGradesService courseGradesService;

    /**
     * 添加成绩
     */
    @PostMapping
    public Result addCourseGrades(@RequestBody @Validated(value = AddGroup.class) CourseGradesDTO courseDTO) {
        CourseGrades course = courseDTO.dtoToDo();
        return courseGradesService.addCourseGrades(course) ? Result.success("添加成绩信息成功") : Result.error("添加成绩信息失败");
    }

    /**
     * 删除成绩
     */
    @DeleteMapping("/{id}")
    public Result deleteCourseGradesById(@PathVariable("id") Integer id) {
        return courseGradesService.deleteCourseGradesById(id) ? Result.success("删除成绩信息成功") : Result.error("删除成绩信息失败");
    }

    /**
     * 修改成绩
     */
     @PutMapping
    public Result editCourseGradesById(@RequestBody @Validated(value = UpdateGroup.class) CourseGradesDTO courseDTO) {
        CourseGrades course = courseDTO.dtoToDo();
        return courseGradesService.editCourseGradesById(course) ? Result.success("更新成绩信息成功") : Result.error("更新成绩信息失败");
    }
}
