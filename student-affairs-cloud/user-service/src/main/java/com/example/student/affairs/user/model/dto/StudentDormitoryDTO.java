package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentDormitory;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/20 1:53
 */
@Data
public class StudentDormitoryDTO implements DTOConvertDO<StudentDormitory> {
    /**
     * 主键ID
     */
    @NotNull(message = "宿舍住宿记录ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 学生ID
     */
    @NotNull(message="学生ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer studentId;

    /**
     * 宿舍ID
     */
    @NotNull(message="宿舍ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer dormitoryId;

    @Override
    public StudentDormitory dtoToDo() {
        return StudentDormitory.builder()
                .id(id)
                .studentId(studentId)
                .dormitoryId(dormitoryId)
                .build();
    }
}
