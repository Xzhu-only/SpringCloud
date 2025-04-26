package com.example.student.affairs.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.DormitoryStayRequest;
import com.example.student.affairs.dormitory.model.dto.DormitoryStayDTO;
import com.example.student.affairs.dormitory.service.DormitoryStayRequestService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 宿舍留校申请接口
 * @date 2024/12/19 19:15
 */
@RestController
@RequestMapping("/dormitory/stay")
public class DormitoryStayController {
    @Resource
    private DormitoryStayRequestService dormitoryStayRequestService;

    /**
     * 分页条件查询留校申请信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody DormitoryStayRequest condition) {
        Page<DormitoryStayRequest> page = dormitoryStayRequestService.getPageByCondition(
                new Page<DormitoryStayRequest>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加留校申请
     */
    @PostMapping
    public Result addDormitoryStayRequest(@RequestBody @Validated(value = AddGroup.class)
                                            DormitoryStayDTO dto) {
        DormitoryStayRequest dormitoryStay = dto.dtoToDo();
        return dormitoryStayRequestService.addDormitoryStayRequest(dormitoryStay) ?
                Result.success("添加留校申请成功") : Result.error("添加留校申请失败");
    }

    /**
     * 删除留校申请
     */
    @DeleteMapping("/{id}")
    public Result deleteDormitoryStayRequestById(@PathVariable("id") Integer id) {
        return dormitoryStayRequestService.deleteDormitoryStayRequestById(id) ?
                Result.success("删除留校申请成功") : Result.error("删除留校申请失败");
    }

    /**
     * 修改留校申请
     */
    @PutMapping
    public Result editDormitoryStayRequestById(@RequestBody @Validated(value = UpdateGroup.class)
                                                 DormitoryStayDTO dto) {
        DormitoryStayRequest dormitoryStay = dto.dtoToDo();
        return dormitoryStayRequestService.editDormitoryStayRequestById(dormitoryStay) ?
                Result.success("更新留校申请成功") : Result.error("更新留校申请失败");
    }
}
