package com.example.student.affairs.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.dormitory.model.DormitoryDisciplineRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【dormitory_discipline_record(宿舍违纪记录表)】的数据库操作Service
* @createDate 2024-12-19 19:03:07
*/
public interface DormitoryDisciplineRecordService extends IService<DormitoryDisciplineRecord> {

    Page<DormitoryDisciplineRecord> getPageByCondition(Page<DormitoryDisciplineRecord> dormitoryDisciplineRecordPage, DormitoryDisciplineRecord condition);

    boolean addDormitoryDisciplineRecord(DormitoryDisciplineRecord dormitoryDiscipline);

    boolean deleteDormitoryDisciplineRecordById(Integer id);

    boolean editDormitoryDisciplineRecordById(DormitoryDisciplineRecord dormitoryDiscipline);
}
