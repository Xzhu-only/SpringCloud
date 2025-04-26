package com.example.student.affairs.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentHealthRecord;
import com.example.student.affairs.user.model.dto.StudentHealthDTO;
import com.example.student.affairs.user.service.StudentHealthRecordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生健康记录接口
 * @date 2024/12/19 19:10
 */
@RestController
@RequestMapping("/student/health_record")
public class StudentHealthRecordController {
    @Resource
    private StudentHealthRecordService studentHealthRecordService;

    /**
     * 分页条件查询健康记录记录信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody StudentHealthRecord condition) {
        Page<StudentHealthRecord> page = studentHealthRecordService.getPageByCondition(
                new Page<StudentHealthRecord>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加健康记录记录
     */
    @PostMapping
    public Result addStudentHealthRecord(@RequestBody @Validated(value = AddGroup.class)
                                         StudentHealthDTO dto) {
        StudentHealthRecord studentHealthRecord = dto.dtoToDo();
        return studentHealthRecordService.addStudentHealthRecord(studentHealthRecord) ?
                Result.success("添加健康记录记录成功") : Result.error("添加健康记录记录失败");
    }

    /**
     * 删除健康记录记录
     */
    @DeleteMapping("/{id}")
    public Result deleteStudentHealthRecordById(@PathVariable("id") Integer id) {
        return studentHealthRecordService.deleteStudentHealthRecordById(id) ?
                Result.success("删除健康记录记录成功") : Result.error("删除健康记录记录失败");
    }

    /**
     * 修改健康记录记录
     */
    @PutMapping
    public Result editStudentHealthRecordById(@RequestBody @Validated(value = UpdateGroup.class)
                                                  StudentHealthDTO dto) {
        StudentHealthRecord studentHealthRecord = dto.dtoToDo();
        return studentHealthRecordService.editStudentHealthRecordById(studentHealthRecord) ?
                Result.success("更新健康记录记录成功") : Result.error("更新健康记录记录失败");
    }
}
