package com.example.student.affairs.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.student.affairs.user.model.User;
import com.example.student.affairs.user.model.dto.UserLoginDTO;

import java.util.List;
import java.util.Map;

/**
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-11-15 16:22:34
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param user 注册参数
     * @return 注册结果
     */
    boolean userRegister(User user);

    /**
     * 用户登录
     * @param dto 登录参数
     * @return 登录结果
     */
    String userLogin(UserLoginDTO dto);

    /**
     * 获取登录信息
     * @return user
     */
    User getLoginInfo();

    /**
     * 分页条件查询用户列表
     * @param page 分页参数
     * @param condition 查询条件
     * @return page
     */
    Page<User> getPageByCondition(Page<User> page, User condition);

    /**
     * 根据角色查询用户列表
     * @param role 角色
     * @return list
     */
    List<User> getListByRole(Integer role);

    /**
     * 添加用户
     * @param user 用户数据
     * @return boolean
     */
    boolean addUser(User user);

    /**
     * 删除用户
     * @param id 用户ID
     * @return boolean
     */
    boolean deleteUserById(Integer id);

    /**
     * 更新用户
     * @param user 用户数据
     * @return boolean
     */
    boolean editUserById(User user);

    /**
     * 根据ID查询用户（feign接口）
     * @param id 用户ID
     * @return user
     */
    User getUserById(Integer id);

    /**
     * 查询毕业流程执行步骤
     * @return int
     */
    Map<String, Integer> getActive();
}
