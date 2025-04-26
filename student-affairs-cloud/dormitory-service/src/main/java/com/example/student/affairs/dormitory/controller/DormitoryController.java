package com.example.student.affairs.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.Dormitory;
import com.example.student.affairs.dormitory.model.dto.DormitoryDTO;
import com.example.student.affairs.dormitory.service.DormitoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宿舍接口
 * @date 2024/12/19 19:15
 */
@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    @Resource
    private DormitoryService dormitoryService;

    /**
     * 分页条件查询宿舍信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody Dormitory condition) {
        Page<Dormitory> page = dormitoryService.getPageByCondition(new Page<Dormitory>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 查询宿舍列表
     */
    @GetMapping("/list")
    public Result getList() {
        List<Dormitory> list = dormitoryService.getList();
        return Result.success(list);
    }

    /**
     * 添加宿舍
     */
    @PostMapping
    public Result addDormitory(@RequestBody @Validated(value = AddGroup.class) DormitoryDTO dormitoryDTO) {
        Dormitory dormitory = dormitoryDTO.dtoToDo();
        return dormitoryService.addDormitory(dormitory) ? Result.success("添加宿舍成功") : Result.error("添加宿舍失败");
    }

    /**
     * 删除宿舍
     */
    @DeleteMapping("/{id}")
    public Result deleteDormitoryById(@PathVariable("id") Integer id) {
        return dormitoryService.deleteDormitoryById(id) ? Result.success("删除宿舍成功") : Result.error("删除宿舍失败");
    }

    /**
     * 修改宿舍
     */
    @PutMapping
    public Result editDormitoryById(@RequestBody @Validated(value = UpdateGroup.class) DormitoryDTO dormitoryDTO) {
        Dormitory dormitory = dormitoryDTO.dtoToDo();
        return dormitoryService.editDormitoryById(dormitory) ? Result.success("更新宿舍成功") : Result.error("更新宿舍失败");
    }

    /**
     * 查询宿舍
     */
    @GetMapping("/{id}")
    public Dormitory getDormitoryById(@PathVariable("id")Integer id) {
        Dormitory dormitory = dormitoryService.getDormitoryById(id);
        return dormitory;
    }

    @PostMapping("/update")
    public boolean updateDormitoryDeign(@RequestBody Dormitory dormitory) {
        return dormitoryService.updateById(dormitory);
    }
}
