package com.example.student.affairs.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.dormitory.model.DormitoryCleanlinessCheck;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【dormitory_cleanliness_check(宿舍卫生检查表)】的数据库操作Service
* @createDate 2024-12-19 19:03:07
*/
public interface DormitoryCleanlinessCheckService extends IService<DormitoryCleanlinessCheck> {

    Page<DormitoryCleanlinessCheck> getPageByCondition(Page<DormitoryCleanlinessCheck> dormitoryCleanlinessCheckPage, DormitoryCleanlinessCheck condition);

    boolean addDormitoryCleanlinessCheck(DormitoryCleanlinessCheck dormitoryCleanliness);

    boolean deleteDormitoryCleanlinessCheckById(Integer id);

    boolean editDormitoryCleanlinessCheckById(DormitoryCleanlinessCheck dormitoryCleanliness);
}
