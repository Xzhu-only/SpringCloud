package com.example.student.affairs.dormitory.model;

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
 * 宿舍违纪记录表
 * @TableName dormitory_discipline_record
 */
@TableName(value ="dormitory_discipline_record")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryDisciplineRecord implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 宿舍ID
     */
    private Integer dormitoryId;

    /**
     * 违纪日期
     */
    private Date incidentDate;

    /**
     * 违纪类型
     */
    private String incidentType;

    /**
     * 违纪详情
     */
    private String incidentDetails;

    /**
     * 处罚措施
     */
    private String punishment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String dormitoryName;
}