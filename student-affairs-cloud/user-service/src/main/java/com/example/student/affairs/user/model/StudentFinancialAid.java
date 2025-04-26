package com.example.student.affairs.user.model;

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
 *  学生资助表
 * @TableName student_financial_aid
 */
@TableName(value ="student_financial_aid")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentFinancialAid implements Serializable {
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
     * 资助类型（如奖学金、助学金等）
     */
    private String aidType;

    /**
     * 资助金额
     */
    private Double aidAmount;

    /**
     * 资助所属学期
     */
    private String semester;

    /**
     * 资助来源（如“学校奖学金”）
     */
    private String aidSource;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 资助状态（待领取，已领取）
     */
    private String state;

    /**
     * 资助日期
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String studentName;
}