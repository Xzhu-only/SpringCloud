package com.example.student.affairs.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StatisticMap;
import com.example.student.affairs.user.model.StudentAwardPunishment;
import com.example.student.affairs.user.model.dto.StudentAwardPunishmentDTO;
import com.example.student.affairs.user.service.StudentAwardPunishmentService;
import com.example.student.affairs.user.service.StudentHealthRecordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生奖惩接口
 * @date 2024/12/19 19:10
 */
@RestController
@RequestMapping("/student/award_punishment")
public class StudentAwardPunishmentController {
    @Resource
    private StudentAwardPunishmentService studentAwardPunishmentService;

    /**
     * 分页条件查询奖惩记录信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody StudentAwardPunishment condition) {
        Page<StudentAwardPunishment> page = studentAwardPunishmentService.getPageByCondition(
                new Page<StudentAwardPunishment>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加奖惩记录
     */
    @PostMapping
    public Result addStudentAwardPunishment(@RequestBody @Validated(value = AddGroup.class)
                                StudentAwardPunishmentDTO dto) {
        StudentAwardPunishment studentAwardPunishment = dto.dtoToDo();
        return studentAwardPunishmentService.addStudentAwardPunishment(studentAwardPunishment) ?
                Result.success("添加奖惩记录成功") : Result.error("添加奖惩记录失败");
    }

    /**
     * 删除奖惩记录
     */
    @DeleteMapping("/{id}")
    public Result deleteStudentAwardPunishmentById(@PathVariable("id") Integer id) {
        return studentAwardPunishmentService.deleteStudentAwardPunishmentById(id) ?
                Result.success("删除奖惩记录成功") : Result.error("删除奖惩记录失败");
    }

    /**
     * 修改奖惩记录
     */
    @PutMapping
    public Result editStudentAwardPunishmentById(@RequestBody @Validated(value = UpdateGroup.class)
                                     StudentAwardPunishmentDTO dto) {
        StudentAwardPunishment studentAwardPunishment = dto.dtoToDo();
        return studentAwardPunishmentService.editStudentAwardPunishmentById(studentAwardPunishment) ?
                Result.success("更新奖惩记录成功") : Result.error("更新奖惩记录失败");
    }

    /**
     * 查询奖惩记录分类
     */
    @GetMapping("/statistic/getEcharts5")
    public Result getEcharts5() {
        List<StatisticMap> list = studentAwardPunishmentService.getEcharts5();
        return Result.success(list);
    }
}
