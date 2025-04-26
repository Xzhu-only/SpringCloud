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
 * 学籍表
 * @TableName student_status
 */
@TableName(value ="student_status")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentStatus implements Serializable {
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
     * 学生姓名
     */
    private String fullName;

    /**
     * 民族
     */
    private String ethnicity;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 入学年份
     */
    private Date enrollmentYear;

    /**
     * 学籍状态（在读、休学、退学等）
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}