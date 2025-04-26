package com.example.student.affairs.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.dormitory.model.Dormitory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhanh
* @description 针对表【dormitory(宿舍表)】的数据库操作Service
* @createDate 2024-12-19 19:03:07
*/
public interface DormitoryService extends IService<Dormitory> {

    Page<Dormitory> getPageByCondition(Page<Dormitory> dormitoryPage, Dormitory condition);

    List<Dormitory> getList();

    boolean addDormitory(Dormitory dormitory);

    boolean deleteDormitoryById(Integer id);

    boolean editDormitoryById(Dormitory dormitory);

    Dormitory getDormitoryById(Integer id);
}
