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
 * 宿舍卫生检查表
 * @TableName dormitory_cleanliness_check
 */
@TableName(value ="dormitory_cleanliness_check")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryCleanlinessCheck implements Serializable {
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
     * 检查日期
     */
    private Date checkDate;

    /**
     * 卫生状况（合格或不合格）
     */
    private String cleanlinessStatus;

    /**
     * 检查结果描述
     */
    private String checkResult;

    /**
     * 备注信息
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String dormitoryName;
}