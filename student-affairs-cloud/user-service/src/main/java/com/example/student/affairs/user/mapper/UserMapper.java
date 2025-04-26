package com.example.student.affairs.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.student.affairs.user.model.User;
import org.apache.ibatis.annotations.Param;

/**
* @description 针对表【users(用户表)】的数据库操作Mapper
* @createDate 2024-11-15 16:22:34
* @Entity com.laboratory.admin.model.domain.Users
*/
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return users
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 根据ID查询用户（忽略逻辑删除）
     * @param id 用户ID
     * @return user
     */
    User selectUserById(@Param("id") Integer id);
}




