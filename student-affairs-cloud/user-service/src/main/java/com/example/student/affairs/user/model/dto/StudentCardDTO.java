package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentCard;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/20 1:53
 */
@Data
public class StudentCardDTO implements DTOConvertDO<StudentCard> {
    /**
     * 主键ID
     */
    @NotNull(message = "学生证ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 学生证状态（学生证状态（申请中、已发放、已失效）
     */
    @NotBlank(message="学生证状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String state;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 所属学生ID
     */
    @NotNull(message="所属学生ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer studentId;

    @Override
    public StudentCard dtoToDo() {
        return StudentCard.builder()
                .id(id)
                .state(state)
                .remarks(remarks)
                .studentId(studentId)
                .build();
    }
}
