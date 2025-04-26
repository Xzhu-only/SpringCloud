package com.example.student.affairs.dormitory.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.DormitoryCleanlinessCheck;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/19 22:06
 */
@Data
public class DormitoryCleanlinessDTO implements DTOConvertDO<DormitoryCleanlinessCheck> {
    /**
     * 主键ID
     */
    @NotNull(message = "检查记录ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 宿舍ID
     */
    @NotNull(message="宿舍ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer dormitoryId;

    /**
     * 卫生状况（合格或不合格）
     */
    @NotBlank(message="宿舍卫生状况不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String cleanlinessStatus;

    /**
     * 检查结果描述
     */
    @NotBlank(message="检查结果描述不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String checkResult;

    /**
     * 备注信息
     */
    private String remark;

    @Override
    public DormitoryCleanlinessCheck dtoToDo() {
        return DormitoryCleanlinessCheck.builder()
                .id(id)
                .dormitoryId(dormitoryId)
                .cleanlinessStatus(cleanlinessStatus)
                .checkResult(checkResult)
                .remark(remark)
                .build();
    }
}
