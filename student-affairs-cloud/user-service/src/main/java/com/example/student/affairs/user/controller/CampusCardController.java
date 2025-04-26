package com.example.student.affairs.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.CampusCard;
import com.example.student.affairs.user.model.dto.CampusCardDTO;
import com.example.student.affairs.user.service.CampusCardService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 校园卡接口
 * @date 2024/12/19 19:09
 */
@RestController
@RequestMapping("/campus_card")
public class CampusCardController {
    @Resource
    private CampusCardService campusCardService;

    /**
     * 分页条件查询校园卡信息
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody CampusCard condition) {
        Page<CampusCard> page = campusCardService.getPageByCondition(
                new Page<CampusCard>(pageNum, pageSize), condition);
        return Result.success(page);
    }

    /**
     * 添加校园卡
     */
    @PostMapping
    public Result addCampusCard(@RequestBody @Validated(value = AddGroup.class)
                                CampusCardDTO dto) {
        CampusCard campusCard = dto.dtoToDo();
        return campusCardService.addCampusCard(campusCard) ?
                Result.success("添加校园卡成功") : Result.error("添加校园卡失败");
    }

    /**
     * 删除校园卡
     */
    @DeleteMapping("/{id}")
    public Result deleteCampusCardById(@PathVariable("id") Integer id) {
        return campusCardService.deleteCampusCardById(id) ?
                Result.success("删除校园卡成功") : Result.error("删除校园卡失败");
    }

    /**
     * 修改校园卡
     */
    @PutMapping
    public Result editCampusCardById(@RequestBody @Validated(value = UpdateGroup.class)
                                                 CampusCardDTO dto) {
        CampusCard campusCard = dto.dtoToDo();
        return campusCardService.editCampusCardById(campusCard) ?
                Result.success("更新校园卡成功") : Result.error("更新校园卡失败");
    }
}
