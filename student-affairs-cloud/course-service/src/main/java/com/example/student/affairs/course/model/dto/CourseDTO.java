package com.example.student.affairs.course.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.course.model.Course;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/19 19:38
 */
@Data
public class CourseDTO implements DTOConvertDO<Course> {
    /**
     * 主键ID
     */
    @NotNull(message = "课程ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 课程名称
     */
    @NotBlank(message="课程名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String courseName;

    /**
     * 课程学分
     */
    @NotNull(message="课程学分不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer credit;

    /**
     * 课程编号
     */
    @NotBlank(message="课程编号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String courseCode;

    /**
     * 课程描述
     */
    @NotBlank(message="课程描述不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String description;

    /**
     * 授课老师ID
     */
    private Integer teacherId;

    @Override
    public Course dtoToDo() {
        return Course.builder()
                .id(id)
                .courseName(courseName)
                .credit(credit)
                .courseCode(courseCode)
                .description(description)
                .teacherId(teacherId)
                .build();
    }
}
