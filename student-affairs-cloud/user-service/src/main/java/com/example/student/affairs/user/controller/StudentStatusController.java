package com.example.student.affairs.user.controller;

import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentStatus;
import com.example.student.affairs.user.model.StudentStatus;
import com.example.student.affairs.user.model.dto.StudentStatusDTO;
import com.example.student.affairs.user.service.StudentStatusService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学籍接口
 * @date 2024/12/21 10:00
 */
@RestController
@RequestMapping("/student/status")
public class StudentStatusController {
    @Resource
    private StudentStatusService studentStatusService;

    /**
     * 添加学籍
     */
    @PostMapping
    public Result addStudentStatus(@RequestBody @Validated(value = AddGroup.class)
                                StudentStatusDTO dto) {
        StudentStatus studentStatus = dto.dtoToDo();
        return studentStatusService.addStudentStatus(studentStatus) ?
                Result.success("添加学籍信息成功") : Result.error("添加学籍信息失败");
    }

    /**
     * 删除学籍
     */
    @DeleteMapping("/{id}")
    public Result deleteStudentStatusById(@PathVariable("id") Integer id) {
        return studentStatusService.deleteStudentStatusById(id) ?
                Result.success("删除学籍信息成功") : Result.error("删除学籍信息失败");
    }

    /**
     * 修改学籍
     */
    @PutMapping
    public Result editStudentStatusById(@RequestBody @Validated(value = UpdateGroup.class)
                                                 StudentStatusDTO dto) {
        StudentStatus studentStatus = dto.dtoToDo();
        return studentStatusService.editStudentStatusById(studentStatus) ?
                Result.success("更新学籍信息成功") : Result.error("更新学籍信息失败");
    }

    /**
     * 查询学籍
     */
    @GetMapping("/get/{userId}")
    public Result getStudentStatus(@PathVariable("userId") Integer userId) {
        StudentStatus studentStatus = studentStatusService.getStudentStatusByStudentId(userId);
        return Result.success(studentStatus);
    }
}
