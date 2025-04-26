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
 * 奖惩表
 * @TableName student_award_punishment
 */
@TableName(value ="student_award_punishment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentAwardPunishment implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 类型（奖励或惩罚）
     */
    private String type;

    /**
     * 奖励或惩罚的原因
     */
    private String reason;

    /**
     * 详细描述
     */
    private String details;

    /**
     * 发生日期
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String studentName;
}