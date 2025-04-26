package com.example.student.affairs.dormitory.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.DormitoryRepairRequest;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/19 22:06
 */
@Data
public class DormitoryRepairDTO implements DTOConvertDO<DormitoryRepairRequest> {
    /**
     * 主键ID
     */
    @NotNull(message = "申请ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 宿舍ID
     */
    @NotNull(message="宿舍ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer dormitoryId;

    /**
     * 问题类型
     */
    @NotBlank(message="问题类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String issueType;

    /**
     * 问题描述
     */
    @NotBlank(message="问题描述不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String issueDescription;

    /**
     * 申请状态（待处理、已完成）
     */
    @NotBlank(message="申请状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String repairStatus;

    @Override
    public DormitoryRepairRequest dtoToDo() {
        return DormitoryRepairRequest.builder()
                .id(id)
                .dormitoryId(dormitoryId)
                .issueType(issueType)
                .issueDescription(issueDescription)
                .repairStatus(repairStatus)
                .build();
    }
}
