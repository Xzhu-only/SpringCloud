package com.example.student.affairs.feign.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseSelection implements Serializable {
    /**
     * 主键ID
     */
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

    private static final long serialVersionUID = 1L;
}