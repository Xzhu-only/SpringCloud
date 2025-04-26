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
 * 学生健康记录表
 * @TableName student_health_record
 */
@TableName(value ="student_health_record")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentHealthRecord implements Serializable {
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
     * 健康状况
     */
    private String healthStatus;

    /**
     * 体重
     */
    private Double weight;

    /**
     * 身高
     */
    private Double height;

    /**
     * 血压
     */
    private Double bloodPressure;

    /**
     * 疫苗接种情况（如“已接种”）
     */
    private String vaccinationStatus;

    /**
     * 过敏史
     */
    private String allergies;

    /**
     * 慢性病史
     */
    private String chronicConditions;

    /**
     * 登记日期
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String studentName;
}