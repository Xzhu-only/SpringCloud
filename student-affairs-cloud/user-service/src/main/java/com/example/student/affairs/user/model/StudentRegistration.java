package com.example.student.affairs.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新生预报登记表
 * @TableName student_registration
 */
@TableName(value ="student_registration")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistration implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 新生姓名
     */
    private String studentName;

    /**
     * 新生学号
     */
    private String studentId;

    /**
     * 性别
     */
    private String gender;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态，未签到、已签到、已取消
     */
    private String state;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 登记时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}