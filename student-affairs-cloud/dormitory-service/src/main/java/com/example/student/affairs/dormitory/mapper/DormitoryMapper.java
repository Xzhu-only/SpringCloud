package com.example.student.affairs.dormitory.mapper;

import com.example.student.affairs.dormitory.model.Dormitory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zhanh
* @description 针对表【dormitory(宿舍表)】的数据库操作Mapper
* @createDate 2024-12-19 19:03:07
* @Entity com.example.student.affairs.dormitory.model.Dormitory
*/
public interface DormitoryMapper extends BaseMapper<Dormitory> {

    Dormitory selectDormitoryById(@Param("id") Integer id);

    List<Dormitory> selectListByTeacherId(@Param("teacherId") Integer teacherId);
}




