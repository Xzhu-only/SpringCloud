package com.example.student.affairs.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.DormitoryRepairRequest;
import com.example.student.affairs.dormitory.model.dto.DormitoryRepairDTO;
import com.example.student.affairs.dormitory.service.DormitoryRepairRequestService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 宿舍报修申请接口
 * @date 2024/12/19 19:15
 */
@RestController
@RequestMapping("/dormitory/repair")
public class DormitoryRepairController {
    @Resource
    private DormitoryRepairRequestService dormitoryRepairRequestService;

    /**
     * 分页条件查询宿舍报修申请信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody DormitoryRepairRequest condition) {
        Page<DormitoryRepairRequest> page = dormitoryRepairRequestService.getPageByCondition(
                new Page<DormitoryRepairRequest>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加宿舍报修申请
     */
    @PostMapping
    public Result addDormitoryRepairRequest(@RequestBody @Validated(value = AddGroup.class)
                                               DormitoryRepairDTO dto) {
        DormitoryRepairRequest dormitoryRepair = dto.dtoToDo();
        return dormitoryRepairRequestService.addDormitoryRepairRequest(dormitoryRepair) ?
                Result.success("添加宿舍报修申请成功") : Result.error("添加宿舍报修申请失败");
    }

    /**
     * 删除宿舍报修申请
     */
    @DeleteMapping("/{id}")
    public Result deleteDormitoryRepairRequestById(@PathVariable("id") Integer id) {
        return dormitoryRepairRequestService.deleteDormitoryRepairRequestById(id) ?
                Result.success("删除宿舍报修申请成功") : Result.error("删除宿舍报修申请失败");
    }

    /**
     * 修改宿舍报修申请
     */
    @PutMapping
    public Result editDormitoryRepairRequestById(@RequestBody @Validated(value = UpdateGroup.class)
                                                    DormitoryRepairDTO dto) {
        DormitoryRepairRequest dormitoryRepair = dto.dtoToDo();
        return dormitoryRepairRequestService.editDormitoryRepairRequestById(dormitoryRepair) ?
                Result.success("更新宿舍报修申请成功") : Result.error("更新宿舍报修申请失败");
    }
}
