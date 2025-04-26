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
 * 宿舍保修申请表
 * @TableName dormitory_repair_request
 */
@TableName(value ="dormitory_repair_request")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryRepairRequest implements Serializable {
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
     * 申请日期
     */
    private Date requestDate;

    /**
     * 问题类型
     */
    private String issueType;

    /**
     * 问题描述
     */
    private String issueDescription;

    /**
     * 申请状态（待处理、已完成）
     */
    private String repairStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String dormitoryName;
}