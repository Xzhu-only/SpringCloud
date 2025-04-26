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
 * 学生证表
 * @TableName student_card
 */
@TableName(value ="student_card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCard implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生证编号
     */
    private String cardNumber;

/**
 * 学生证状态（学生证状态（申请中、已发放、已失效）
 */
    private String state;

    /**
     * 申请日期
     */
    private Date applicationDate;

    /**
     * 发放日期
     */
    private Date issueDate;

    /**
     * 过期日期
     */
    private Date expirationDate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 所属学生ID
     */
    private Integer studentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String studentName;
}