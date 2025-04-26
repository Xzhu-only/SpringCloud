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
 * 校园卡表
 * @TableName campus_card
 */
@TableName(value ="campus_card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampusCard implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 校园卡编号
     */
    private String cardNumber;

    /**
     * 校园卡余额
     */
    private Double balance;

    /**
     * 校园卡状态
     */
    private String state;

    /**
     * 发放日期
     */
    private Date issuedDate;

    /**
     * 最后重置时间
     */
    private Date lastRechargeDate;

    /**
     * 所属学生ID
     */
    private Integer studentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String studentName;
}