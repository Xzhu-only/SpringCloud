package com.example.student.affairs.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.example.student.affairs.feign.model.Dormitory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  学生宿舍表
 * @TableName student_dormitory
 */
@TableName(value ="student_dormitory")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDormitory implements Serializable {
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
     * 宿舍ID
     */
    private Integer dormitoryId;

    /**
     * 入住日期
     */
    private Date moveInDate;

    /**
     * 退宿日期
     */
    private Date moveOutDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String studentName;

    @TableField(exist = false)
    private Dormitory dormitory;
}