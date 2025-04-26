package com.example.student.affairs.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentFinancialAid;
import com.example.student.affairs.user.model.dto.StudentFinancialAidDTO;
import com.example.student.affairs.user.service.StudentFinancialAidService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生资助接口
 * @date 2024/12/19 19:10
 */
@RestController
@RequestMapping("/student/financial_aid")
public class StudentFinancialAidController {
    @Resource
    private StudentFinancialAidService studentFinancialAidService;

    /**
     * 分页条件查询资助信息记录信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody StudentFinancialAid condition) {
        Page<StudentFinancialAid> page = studentFinancialAidService.getPageByCondition(
                new Page<StudentFinancialAid>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加资助信息记录
     */
    @PostMapping
    public Result addStudentFinancialAid(@RequestBody @Validated(value = AddGroup.class)
                                      StudentFinancialAidDTO dto) {
        StudentFinancialAid studentFinancialAid = dto.dtoToDo();
        return studentFinancialAidService.addStudentFinancialAid(studentFinancialAid) ?
                Result.success("添加资助信息记录成功") : Result.error("添加资助信息记录失败");
    }

    /**
     * 删除资助信息记录
     */
    @DeleteMapping("/{id}")
    public Result deleteStudentFinancialAidById(@PathVariable("id") Integer id) {
        return studentFinancialAidService.deleteStudentFinancialAidById(id) ?
                Result.success("删除资助信息记录成功") : Result.error("删除资助信息记录失败");
    }

    /**
     * 修改资助信息记录
     */
    @PutMapping
    public Result editStudentFinancialAidById(@RequestBody @Validated(value = UpdateGroup.class)
                                           StudentFinancialAidDTO dto) {
        StudentFinancialAid studentFinancialAid = dto.dtoToDo();
        return studentFinancialAidService.editStudentFinancialAidById(studentFinancialAid) ?
                Result.success("更新资助信息记录成功") : Result.error("更新资助信息记录失败");
    }
}
