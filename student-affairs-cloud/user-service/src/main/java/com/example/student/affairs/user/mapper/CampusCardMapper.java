package com.example.student.affairs.user.mapper;

import com.example.student.affairs.user.model.CampusCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zhanh
* @description 针对表【campus_card(校园卡表)】的数据库操作Mapper
* @createDate 2024-12-19 19:00:58
* @Entity com.example.student.affairs.user.model.CampusCard
*/
public interface CampusCardMapper extends BaseMapper<CampusCard> {

    CampusCard selectByStudentId(@Param("studentId") Integer studentId);
}




