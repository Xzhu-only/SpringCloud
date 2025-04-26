package com.example.student.affairs.dormitory.model;

import com.alibaba.nacos.shaded.org.checkerframework.common.value.qual.ArrayLen;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 宿舍表
 * @TableName dormitory
 */
@TableName(value ="dormitory")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dormitory implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
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
    @TableLogic
    private Integer deleteFlag;

    /**
     * 负责老师ID
     */
    private Integer teacherId;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String teacherName;
}