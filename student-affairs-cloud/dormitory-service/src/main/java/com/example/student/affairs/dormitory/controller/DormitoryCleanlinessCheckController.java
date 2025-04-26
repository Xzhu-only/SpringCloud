package com.example.student.affairs.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.DormitoryCleanlinessCheck;
import com.example.student.affairs.dormitory.model.dto.DormitoryCleanlinessDTO;
import com.example.student.affairs.dormitory.service.DormitoryCleanlinessCheckService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 宿舍检查记录接口
 * @date 2024/12/19 19:15
 */
@RestController
@RequestMapping("/dormitory/cleanliness_check")
public class DormitoryCleanlinessCheckController {
    @Resource
    private DormitoryCleanlinessCheckService dormitoryCleanlinessCheckService;

    /**
     * 分页条件查询宿舍检查记录信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody DormitoryCleanlinessCheck condition) {
        Page<DormitoryCleanlinessCheck> page = dormitoryCleanlinessCheckService.getPageByCondition(
                new Page<DormitoryCleanlinessCheck>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加宿舍检查记录
     */
    @PostMapping
    public Result addDormitoryCleanlinessCheck(@RequestBody @Validated(value = AddGroup.class)
                                               DormitoryCleanlinessDTO dto) {
        DormitoryCleanlinessCheck dormitoryCleanliness = dto.dtoToDo();
        return dormitoryCleanlinessCheckService.addDormitoryCleanlinessCheck(dormitoryCleanliness) ?
                Result.success("添加宿舍检查记录成功") : Result.error("添加宿舍检查记录失败");
    }

    /**
     * 删除宿舍检查记录
     */
    @DeleteMapping("/{id}")
    public Result deleteDormitoryCleanlinessCheckById(@PathVariable("id") Integer id) {
        return dormitoryCleanlinessCheckService.deleteDormitoryCleanlinessCheckById(id) ?
                Result.success("删除宿舍检查记录成功") : Result.error("删除宿舍检查记录失败");
    }

    /**
     * 修改宿舍检查记录
     */
    @PutMapping
    public Result editDormitoryCleanlinessCheckById(@RequestBody @Validated(value = UpdateGroup.class)
                                                        DormitoryCleanlinessDTO dto) {
        DormitoryCleanlinessCheck dormitoryCleanliness = dto.dtoToDo();
        return dormitoryCleanlinessCheckService.editDormitoryCleanlinessCheckById(dormitoryCleanliness) ?
                Result.success("更新宿舍检查记录成功") : Result.error("更新宿舍检查记录失败");
    }
}
