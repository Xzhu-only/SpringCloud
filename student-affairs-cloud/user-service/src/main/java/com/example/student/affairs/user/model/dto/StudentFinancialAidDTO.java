package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentFinancialAid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/20 1:53
 */
@Data
public class StudentFinancialAidDTO implements DTOConvertDO<StudentFinancialAid> {
    /**
     * 主键ID
     */
    @NotNull(message = "资助记录ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 学生ID
     */
    @NotNull(message="学生ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer studentId;

    /**
     * 资助类型（如奖学金、助学金等）
     */
    @NotBlank(message="资助类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String aidType;

    /**
     * 资助金额
     */
    @NotNull(message="资助金额不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Double aidAmount;

    /**
     * 资助所属学期
     */
    @NotBlank(message="资助所属学期不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String semester;

    /**
     * 资助来源（如“学校奖学金”）
     */
    @NotBlank(message="资助来源不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String aidSource;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 资助状态（待领取，已领取）
     */
    @NotBlank(message="资助状态修改时不能为空", groups = {UpdateGroup.class})
    private String state;

    @Override
    public StudentFinancialAid dtoToDo() {
        return StudentFinancialAid.builder()
                .id(id)
                .studentId(studentId)
                .aidType(aidType)
                .aidAmount(aidAmount)
                .semester(semester)
                .aidSource(aidSource)
                .remark(remark)
                .state(state)
                .build();
    }
}
