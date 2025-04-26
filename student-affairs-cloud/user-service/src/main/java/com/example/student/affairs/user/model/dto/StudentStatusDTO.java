package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @date 2024/12/21 10:01
 */
@Data
public class StudentStatusDTO implements DTOConvertDO<StudentStatus> {
    /**
     * 主键ID
     */
    @NotNull(message = "学籍ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 学生ID
     */
    @NotNull(message="学生ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer studentId;

    /**
     * 学生姓名
     */
    @NotBlank(message="学生姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String fullName;

    /**
     * 民族
     */
    @NotBlank(message="民族不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String ethnicity;

    /**
     * 籍贯
     */
    @NotBlank(message="籍贯不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String nativePlace;

    /**
     * 身份证
     */
    @NotBlank(message="身份证不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String idCard;

    /**
     * 入学年份
     */
    @NotNull(message="入学年份不能为空", groups = {AddGroup.class})
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
    private Date enrollmentYear;

    /**
     * 学籍状态（在读、休学、退学等）
     */
    @NotBlank(message="学籍状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String state;

    @Override
    public StudentStatus dtoToDo() {
        return StudentStatus.builder()
                .id(id)
                .studentId(studentId)
                .fullName(fullName)
                .ethnicity(ethnicity)
                .nativePlace(nativePlace)
                .idCard(idCard)
                .enrollmentYear(enrollmentYear)
                .state(state)
                .build();
    }
}
