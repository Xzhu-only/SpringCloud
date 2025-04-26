package com.example.student.affairs.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.dormitory.model.DormitoryStayRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【dormitory_stay_request(宿舍留校申请表)】的数据库操作Service
* @createDate 2024-12-19 19:03:07
*/
public interface DormitoryStayRequestService extends IService<DormitoryStayRequest> {

    Page<DormitoryStayRequest> getPageByCondition(Page<DormitoryStayRequest> dormitoryStayRequestPage, DormitoryStayRequest condition);

    boolean addDormitoryStayRequest(DormitoryStayRequest dormitoryStay);

    boolean deleteDormitoryStayRequestById(Integer id);

    boolean editDormitoryStayRequestById(DormitoryStayRequest dormitoryStay);
}
