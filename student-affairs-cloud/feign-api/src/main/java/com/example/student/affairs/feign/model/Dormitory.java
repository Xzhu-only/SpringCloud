package com.example.student.affairs.feign.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dormitory implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 宿舍名称
     */
    private String dormitoryName;

    /**
     * 宿舍楼名称
     */
    private String buildingName;

    /**
     * 宿舍最大容纳人数
     */
    private Integer capacity;

    /**
     * 当前已入住人数
     */
    private Integer currentOccupancy;

    /**
     * 逻辑删除
     */
    private Integer deleteFlag;

    /**
     * 负责老师ID
     */
    private Integer teacherId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    private String teacherName;
}