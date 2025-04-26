package com.example.student.affairs.feign.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户状态，0是正常，1是封禁中
     */
    private Integer state;

    /**
     * 用户角色，0是管理员，1是学生，2是教师
     */
    private Integer role;

    /**
     * 逻辑删除标志
     */
    private Integer deleteFlag;

    /**
     * 注册时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

}
