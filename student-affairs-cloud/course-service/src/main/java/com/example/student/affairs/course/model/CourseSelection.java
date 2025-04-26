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
 * 选课表
 * @TableName course_selection
 */
@TableName(value ="course_selection")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseSelection implements Serializable {
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
     * 课程ID
     */
    private Integer courseId;

    /**
     * 选课日期
     */
    private Date selectionDate;

    /**
     * 学期
     */
    private String semester;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Course course;

    @TableField(exist = false)
    private CourseGrades courseGrades;

    @TableField(exist = false)
    private String studentName;
}