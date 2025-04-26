package com.example.student.affairs.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentCard;
import com.example.student.affairs.user.model.dto.StudentCardDTO;
import com.example.student.affairs.user.service.StudentCardService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生证接口
 * @date 2024/12/19 19:10
 */
@RestController
@RequestMapping("/student/card")
public class StudentCardController {
    @Resource
    private StudentCardService studentCardService;

    /**
     * 分页条件查询学生证信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody StudentCard condition) {
        Page<StudentCard> page = studentCardService.getPageByCondition(
                new Page<StudentCard>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加学生证
     */
    @PostMapping
    public Result addStudentCard(@RequestBody @Validated(value = AddGroup.class)
                                            StudentCardDTO dto) {
        StudentCard studentCard = dto.dtoToDo();
        return studentCardService.addStudentCard(studentCard) ?
                Result.success("添加学生证成功") : Result.error("添加学生证失败");
    }

    /**
     * 删除学生证
     */
    @DeleteMapping("/{id}")
    public Result deleteStudentCardById(@PathVariable("id") Integer id) {
        return studentCardService.deleteStudentCardById(id) ?
                Result.success("删除学生证成功") : Result.error("删除学生证失败");
    }

    /**
     * 修改学生证
     */
    @PutMapping
    public Result editStudentCardById(@RequestBody @Validated(value = UpdateGroup.class)
                                                 StudentCardDTO dto) {
        StudentCard studentCard = dto.dtoToDo();
        return studentCardService.editStudentCardById(studentCard) ?
                Result.success("更新学生证成功") : Result.error("更新学生证失败");
    }
}
