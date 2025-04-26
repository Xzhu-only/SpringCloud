package com.example.student.affairs.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.user.model.CampusCard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhanh
* @description 针对表【campus_card(校园卡表)】的数据库操作Service
* @createDate 2024-12-19 19:00:58
*/
public interface CampusCardService extends IService<CampusCard> {

    Page<CampusCard> getPageByCondition(Page<CampusCard> campusCardPage, CampusCard condition);

    boolean addCampusCard(CampusCard campusCard);

    boolean deleteCampusCardById(Integer id);

    boolean editCampusCardById(CampusCard campusCard);
}
