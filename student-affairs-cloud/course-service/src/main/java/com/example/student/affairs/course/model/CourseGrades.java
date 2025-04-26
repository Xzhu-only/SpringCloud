package com.example.student.affairs.course.model;

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
 * 成绩表
 * @TableName course_grades
 */
@TableName(value ="course_grades")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseGrades implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Integer id;

    /**
     * 选课ID
     */
    private Integer selectionId;

    /**
     * 成绩
     */
    private Double grade;

    /**
     * 成绩录入日期
     */
    private Date gradeDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}