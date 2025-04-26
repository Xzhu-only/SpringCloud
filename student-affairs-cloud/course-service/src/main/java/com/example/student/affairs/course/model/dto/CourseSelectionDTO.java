package com.example.student.affairs.course.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.course.model.CourseSelection;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/19 19:38
 */
@Data
public class CourseSelectionDTO implements DTOConvertDO<CourseSelection> {
    /**
     * 主键ID
     */
    @NotNull(message = "选课信息ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 学生ID
     */
    @NotNull(message="学生ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer studentId;

    /**
     * 课程ID
     */
    @NotNull(message="课程ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer courseId;

    /**
     * 学期
     */
    @NotBlank(message="学期不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String semester;

    @Override
    public CourseSelection dtoToDo() {
        return CourseSelection.builder()
                .id(id)
                .studentId(studentId)
                .courseId(courseId)
                .semester(semester)
                .build();
    }
}
