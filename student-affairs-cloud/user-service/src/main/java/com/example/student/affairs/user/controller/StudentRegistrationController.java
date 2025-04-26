package com.example.student.affairs.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentRegistration;
import com.example.student.affairs.user.model.dto.StudentRegistrationDTO;
import com.example.student.affairs.user.service.StudentRegistrationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @date 2024/12/20 21:27
 */
@RestController
@RequestMapping("/student/registration")
public class StudentRegistrationController {
    @Resource
    private StudentRegistrationService studentRegistrationService;

    /**
     * 分页条件查询登记记录
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody StudentRegistration condition) {
        Page<StudentRegistration> page = studentRegistrationService.getPageByCondition(
                new Page<StudentRegistration>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加记录
     */
    @PostMapping
    public Result addStudentRegistration(@RequestBody @Validated(value = AddGroup.class)
                                            StudentRegistrationDTO dto) {
        StudentRegistration studentRegistration = dto.dtoToDo();
        return studentRegistrationService.addStudentRegistration(studentRegistration) ?
                Result.success("添加新生登记记录成功") : Result.error("添加新生登记记录失败");
    }

    /**
     * 删除记录
     */
    @DeleteMapping("/{id}")
    public Result deleteStudentRegistrationById(@PathVariable("id") Integer id) {
        return studentRegistrationService.deleteStudentRegistrationById(id) ?
                Result.success("删除新生登记记录成功") : Result.error("删除新生登记记录失败");
    }

    /**
     * 修改记录
     */
    @PutMapping
    public Result editStudentRegistrationById(@RequestBody @Validated(value = UpdateGroup.class)
                                                StudentRegistrationDTO dto) {
    StudentRegistration studentRegistration = dto.dtoToDo();
    return studentRegistrationService.editStudentRegistrationById(studentRegistration) ?
            Result.success("更新新生登记记录成功") : Result.error("更新新生登记记录失败");
    }
}
