package com.example.student.affairs.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentDormitory;
import com.example.student.affairs.user.model.dto.StudentDormitoryDTO;
import com.example.student.affairs.user.service.StudentDormitoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生宿舍接口
 * @date 2024/12/19 19:10
 */
    @RestController
    @RequestMapping("/student/dormitory")
    public class StudentDormitoryController {
        @Resource
        private StudentDormitoryService studentDormitoryService;

        /**
         * 分页条件查询学生宿舍记录信息
         */
        @PostMapping("/page/{pageNum}/{pageSize}")
        public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                         @PathVariable("pageSize") Integer pageSize,
                                         @RequestBody StudentDormitory condition) {
            Page<StudentDormitory> page = studentDormitoryService.getPageByCondition(
                    new Page<StudentDormitory>(pageNum, pageSize), condition);
            return Result.success(page);
        }

        /**
         * 添加学生宿舍记录
         */
        @PostMapping
        public Result addStudentDormitory(@RequestBody @Validated(value = AddGroup.class)
                                     StudentDormitoryDTO dto) {
            StudentDormitory studentDormitory = dto.dtoToDo();
            return studentDormitoryService.addStudentDormitory(studentDormitory) ?
                    Result.success("添加学生宿舍记录成功") : Result.error("添加学生宿舍记录失败");
        }

        /**
         * 删除学生宿舍记录
         */
        @DeleteMapping("/{id}")
        public Result deleteStudentDormitoryById(@PathVariable("id") Integer id) {
            return studentDormitoryService.deleteStudentDormitoryById(id) ?
                    Result.success("删除学生宿舍记录成功") : Result.error("删除学生宿舍记录失败");
        }

        /**
         * 修改学生宿舍记录
         */
        @PutMapping
        public Result editStudentDormitoryById(@RequestBody @Validated(value = UpdateGroup.class)
                                          StudentDormitoryDTO dto) {
            StudentDormitory studentDormitory = dto.dtoToDo();
            return studentDormitoryService.editStudentDormitoryById(studentDormitory) ?
                    Result.success("更新学生宿舍记录成功") : Result.error("更新学生宿舍记录失败");
        }


    }
