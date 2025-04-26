package com.example.student.affairs.course.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.course.model.CourseGrades;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/19 19:38
 */
@Data
public class CourseGradesDTO implements DTOConvertDO<CourseGrades> {
    /**
     * 主键ID
     */
    @NotNull(message = "成绩信息ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 选课ID
     */
    @NotNull(message="选课ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer selectionId;

    /**
     * 成绩
     */
    @NotNull(message="成绩不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Double grade;

    @Override
    public CourseGrades dtoToDo() {
        return CourseGrades.builder()
                .id(id)
                .selectionId(selectionId)
                .grade(grade)
                .build();
    }
}
