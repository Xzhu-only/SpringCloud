package com.example.student.affairs.user.mapper;

import com.example.student.affairs.user.model.StudentDormitory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zhanh
* @description 针对表【student_dormitory( 学生宿舍表)】的数据库操作Mapper
* @createDate 2024-12-19 19:03:46
* @Entity com.example.student.affairs.user.model.StudentDormitory
*/
public interface StudentDormitoryMapper extends BaseMapper<StudentDormitory> {

    StudentDormitory selectByStudentId(@Param("studentId") Integer studentId);
}




