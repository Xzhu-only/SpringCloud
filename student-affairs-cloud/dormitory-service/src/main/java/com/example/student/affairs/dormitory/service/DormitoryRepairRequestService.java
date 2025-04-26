package com.example.student.affairs.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.dormitory.model.DormitoryRepairRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【dormitory_repair_request(宿舍保修申请表)】的数据库操作Service
* @createDate 2024-12-19 19:03:07
*/
public interface DormitoryRepairRequestService extends IService<DormitoryRepairRequest> {

    Page<DormitoryRepairRequest> getPageByCondition(Page<DormitoryRepairRequest> dormitoryRepairRequestPage, DormitoryRepairRequest condition);

    boolean addDormitoryRepairRequest(DormitoryRepairRequest dormitoryRepair);

    boolean deleteDormitoryRepairRequestById(Integer id);

    boolean editDormitoryRepairRequestById(DormitoryRepairRequest dormitoryRepair);
}
