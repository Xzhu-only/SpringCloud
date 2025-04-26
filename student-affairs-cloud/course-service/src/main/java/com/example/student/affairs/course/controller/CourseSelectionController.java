package com.example.student.affairs.course.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.course.model.CourseSelection;
import com.example.student.affairs.course.model.dto.CourseSelectionDTO;
import com.example.student.affairs.course.service.CourseSelectionService;
import org.springframework.boot.autoconfigure.security.oauth2.resource.IssuerUriCondition;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2024/12/19 19:06
 */
@RestController
@RequestMapping("/course/selection")
public class CourseSelectionController {
    @Resource
    private CourseSelectionService courseSelectionService;

    /**
     * 分页条件查询选课列表
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody CourseSelection condition) {
        Page<CourseSelection> voPage = courseSelectionService.getPageByCondition(new Page<CourseSelection>(pageNum, pageSize), condition);
        return Result.success(voPage);
    }

    /**
     * 添加选课信息
     */
    @PostMapping
    public Result addCourseSelection(@RequestBody @Validated(value = AddGroup.class) CourseSelectionDTO courseDTO) {
        CourseSelection course = courseDTO.dtoToDo();
        return courseSelectionService.addCourseSelection(course) ? Result.success("添加选课信息成功") : Result.error("添加选课信息失败");
    }

    /**
     * 删除选课信息
     */
    @DeleteMapping("/{id}")
    public Result deleteCourseSelectionById(@PathVariable("id") Integer id) {
        return courseSelectionService.deleteCourseSelectionById(id) ? Result.success("删除选课信息成功") : Result.error("删除选课信息失败");
    }

    /**
     * 修改选课信息
     */
    @PutMapping
    public Result editCourseSelectionById(@RequestBody @Validated(value = UpdateGroup.class) CourseSelectionDTO courseDTO) {
        CourseSelection course = courseDTO.dtoToDo();
        return courseSelectionService.editCourseSelectionById(course) ? Result.success("更新选课信息成功") : Result.error("更新选课信息失败");
    }

    /**
     * 查询选课记录
     */
    @GetMapping("/list")
    public List<CourseSelection> getList() {
        return courseSelectionService.getList();
    }
}
