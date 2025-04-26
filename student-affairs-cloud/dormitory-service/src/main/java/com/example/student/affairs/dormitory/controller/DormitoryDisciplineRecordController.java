package com.example.student.affairs.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.DormitoryDisciplineRecord;
import com.example.student.affairs.dormitory.model.dto.DormitoryDisciplineDTO;
import com.example.student.affairs.dormitory.service.DormitoryDisciplineRecordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 宿舍违纪记录接口
 * @date 2024/12/19 19:15
 */
@RestController
@RequestMapping("/dormitory/discipline_record")
public class DormitoryDisciplineRecordController {
    @Resource
    private DormitoryDisciplineRecordService dormitoryDisciplineRecordService;

    /**
     * 分页条件查询宿舍违纪记录信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody DormitoryDisciplineRecord condition) {
        Page<DormitoryDisciplineRecord> page = dormitoryDisciplineRecordService.getPageByCondition(
                new Page<DormitoryDisciplineRecord>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加宿舍违纪记录
     */
    @PostMapping
    public Result addDormitoryDisciplineRecord(@RequestBody @Validated(value = AddGroup.class)
                                               DormitoryDisciplineDTO dto) {
        DormitoryDisciplineRecord dormitoryDiscipline = dto.dtoToDo();
        return dormitoryDisciplineRecordService.addDormitoryDisciplineRecord(dormitoryDiscipline) ?
                Result.success("添加宿舍违纪记录成功") : Result.error("添加宿舍违纪记录失败");
    }

    /**
     * 删除宿舍违纪记录
     */
    @DeleteMapping("/{id}")
    public Result deleteDormitoryDisciplineRecordById(@PathVariable("id") Integer id) {
        return dormitoryDisciplineRecordService.deleteDormitoryDisciplineRecordById(id) ?
                Result.success("删除宿舍违纪记录成功") : Result.error("删除宿舍违纪记录失败");
    }

    /**
     * 修改宿舍违纪记录
     */
    @PutMapping
    public Result editDormitoryDisciplineRecordById(@RequestBody @Validated(value = UpdateGroup.class)
                                                    DormitoryDisciplineDTO dto) {
        DormitoryDisciplineRecord dormitoryDiscipline = dto.dtoToDo();
        return dormitoryDisciplineRecordService.editDormitoryDisciplineRecordById(dormitoryDiscipline) ?
                Result.success("更新宿舍违纪记录成功") : Result.error("更新宿舍违纪记录失败");
    }
}
