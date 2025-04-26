package com.example.student.affairs.user.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.User;
import com.example.student.affairs.user.model.dto.UserDTO;
import com.example.student.affairs.user.model.dto.UserLoginDTO;
import com.example.student.affairs.user.model.dto.UserRegisterDTO;
import com.example.student.affairs.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result userRegister(@RequestBody @Validated UserRegisterDTO dto) {
        User user = dto.dtoToDo();
        return userService.userRegister(user) ? Result.success("用户注册成功！") : Result.error("用户注册失败！");
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result userLogin(@RequestBody @Validated UserLoginDTO dto) {
        String token = userService.userLogin(dto);
        return StrUtil.isNotBlank(token) ? Result.success("用户登录成功！").put("data", null)
                .put("token", token) : Result.error("用户登录失败！");
    }

    /**
     * 查询登录信息
     */
    @GetMapping("/loginInfo")
    public Result getLoginInfo(){
        User user = userService.getLoginInfo();
        return user != null ? Result.success(user) : Result.error("用户未登录！");
    }

    /**
     * 分页条件查询用户列表
     */
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @RequestBody User condition) {
        Page<User> voPage = userService.getPageByCondition(new Page<User>(pageNum, pageSize), condition);
        return Result.success(voPage);
    }

    /**
     * 根据角色查询用户列表
     */
    @GetMapping("/list/{role}")
    public Result getListByRole(@PathVariable("role") @NotNull(message = "查询角色不能为空") Integer role) {
        List<User> list = userService.getListByRole(role);
        return Result.success(list);
    }

    /**
     * 添加用户
     */
    @PostMapping
    public Result addUser(@RequestBody @Validated UserDTO userDTO) {
        User user = userDTO.dtoToDo();
        return userService.addUser(user) ? Result.success("添加用户成功") : Result.error("添加用户失败");
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public Result deleteUserById(@PathVariable("id") Integer id) {
        return userService.deleteUserById(id) ? Result.success("删除用户成功") : Result.error("删除用户失败");
    }

    /**
     * 更新用户
     */
    @PutMapping
    public Result editUserById(@RequestBody @Validated(value = UpdateGroup.class) UserDTO userDTO) {
        User user = userDTO.dtoToDo();
        return userService.editUserById(user) ? Result.success("更新用户成功") : Result.error("更新用户失败");
    }

    /**
     * 查询毕业流程执行步骤
     */
    @GetMapping("/graduate/active")
    public Result getActive() {
        Map<String, Integer> map = userService.getActive();
        Integer active = map.get("active");
        Integer id = map.get("id");
        return Result.success(active).put("id", id);
    }

    /**
     * 根据ID查询用户（feign接口）
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return user;
    }
}
