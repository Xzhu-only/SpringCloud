package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentAwardPunishment;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/20 1:53
 */
@Data
public class StudentAwardPunishmentDTO implements DTOConvertDO<StudentAwardPunishment> {
    /**
     * 主键ID
     */
    @NotNull(message = "奖惩记录ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 学生ID
     */
    @NotNull(message="学生ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer studentId;

    /**
     * 类型（奖励或惩罚）
     */
    @NotBlank(message="奖惩类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String type;

    /**
     * 奖励或惩罚的原因
     */
    @NotBlank(message="奖惩原因不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String reason;

    /**
     * 详细描述
     */
    @NotBlank(message="详细描述不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String details;

    @Override
    public StudentAwardPunishment dtoToDo() {
        return StudentAwardPunishment.builder()
                .id(id)
                .studentId(studentId)
                .type(type)
                .reason(reason)
                .details(details)
                .build();
    }
}
