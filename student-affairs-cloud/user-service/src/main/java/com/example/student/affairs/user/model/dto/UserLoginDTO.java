package com.example.student.affairs.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 用户登录入参
 */
@Data
public class UserLoginDTO {
    /**
     * 用户名
     */
    @NotBlank(message="登录用户名不能为空")
    @Size(min = 2, max = 15, message = "用户名长度必须在3到15个字符之间")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message="登录密码不能为空")
    @Size(min = 6, max = 15, message = "密码长度必须在6到15个字符之间")
    private String password;
}
