package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.user.model.User;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 用户注册入参
 */
@Data
public class UserRegisterDTO implements DTOConvertDO<User> {
    /**
     * 用户名
     * 校验用户名长度在3到15之间
     */
    @NotBlank(message = "注册时用户名不能为空")
    @Size(min = 2, max = 15, message = "用户名长度必须在3到15个字符之间")
    private String username;

    /**
     * 密码
     * 校验密码长度在6到15之间
     */
    @NotBlank(message = "注册时密码不能为空")
    @Size(min = 6, max = 15, message = "密码长度必须在6到15个字符之间")
    private String password;

    /**
     * 邮箱
     * 使用正则校验邮箱格式
     */
    @NotBlank(message = "注册时邮箱不能为空")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "邮箱格式不正确")
    private String email;

    /**
     * 用户头像
     * 使用正则校验头像是否是一个有效的网址
     */
    @NotBlank(message = "注册时用户头像不能为空")
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[\\S]*$", message = "头像必须是有效的网址地址")
    private String avatar;

    /**
     * 用户角色，0是管理员、1是顾客、2是商家
     * 校验角色是否是1、2、3、4其中一个
     */
    @NotNull(message = "注册时用户角色不能为空")
    @Min(value = 1, message = "用户注册角色非法")
    @Max(value = 5, message = "用户注册角色非法")
    private Integer role;

    @Override
    public User dtoToDo() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .avatar(avatar)
                .role(role)
                .createTime(new Date()).build();
    }
}
