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
 * 宿舍留校申请表
 * @TableName dormitory_stay_request
 */
@TableName(value ="dormitory_stay_request")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryStayRequest implements Serializable {
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
     * 留校起始日期
     */
    private Date stayPeriodStart;

    /**
     * 留校结束日期
     */
    private Date stayPeriodEnd;

    /**
     * 留校原因
     */
    private String reason;

    /**
     * 审批状态（待审批, 批准, 拒绝）
     */
    private String approvalStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String dormitoryName;

    @TableField(exist = false)
    private String studentName;
}