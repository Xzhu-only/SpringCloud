package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.User;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * 用户添加/修改入参
 */
@Data
public class UserDTO implements DTOConvertDO<User> {
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    @Size(min = 2, max = 15, message = "用户名长度必须在2到15个字符之间")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message="密码不能为空", groups = {AddGroup.class})
    @Size(min = 6, max = 15, message = "密码长度必须在6到15个字符之间", groups = {AddGroup.class})
    private String password;

    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "邮箱格式不正确")
    private String email;

    /**
     * 用户头像
     */
    @NotBlank(message="头像不能为空")
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[\\S]*$", message = "头像必须是有效的网址地址")
    private String avatar;

    /**
     * 用户状态，0是正常，1是封禁中
     */
    @NotNull(message="用户状态不能为空", groups = {UpdateGroup.class})
    @Min(value = 0, message = "用户状态信息非法")
    @Max(value = 1, message = "用户状态信息非法")
    private Integer state;

    /**
     * 用户角色，0是管理员，1是实验室管理员，2是分管实训科科长，3是分管副院长，4是教师
     */
    @NotNull(message="用户角色不能为空")
    @Min(value = 1, message = "用户角色非法")
    @Max(value = 5, message = "用户角色非法")
    private Integer role;

    @Override
    public User dtoToDo() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .avatar(avatar)
                .state(state)
                .role(role).build();
    }
}
